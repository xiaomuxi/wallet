//
//  JDScanViewController.m
//  WeexDemo
//
//  Created by 尹路佳 on 2019/4/15.
//  Copyright © 2019 taobao. All rights reserved.
//

#import "JDScanViewController.h"
#import <AVFoundation/AVFoundation.h>

@interface JDScanViewController ()     <AVCaptureMetadataOutputObjectsDelegate>
@property (nonatomic, strong) AVCaptureSession * session;
@property (nonatomic, strong) AVCaptureVideoPreviewLayer *captureLayer;
@property (nonatomic, strong) UIView *sanFrameView;
@end

@implementation JDScanViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self setup];
}

-(void)setup{
#if !(TARGET_IPHONE_SIMULATOR)
    self.session = [[AVCaptureSession alloc]init];
    [_session setSessionPreset:AVCaptureSessionPresetHigh];
    AVCaptureDevice * device = [AVCaptureDevice defaultDeviceWithMediaType:AVMediaTypeVideo];
    AVCaptureDeviceInput * input = [AVCaptureDeviceInput deviceInputWithDevice:device error:nil];
    AVCaptureMetadataOutput * output = [[AVCaptureMetadataOutput alloc]init];
    if (output && input && device) {
        [output setMetadataObjectsDelegate:self queue:dispatch_get_main_queue()];
        [_session addInput:input];
        [_session addOutput:output];
        output.metadataObjectTypes=@[AVMetadataObjectTypeQRCode,AVMetadataObjectTypeEAN13Code, AVMetadataObjectTypeEAN8Code, AVMetadataObjectTypeCode128Code];
    }
    
    _captureLayer = [AVCaptureVideoPreviewLayer layerWithSession:_session];
    _captureLayer.videoGravity=AVLayerVideoGravityResizeAspectFill;
    _captureLayer.frame=self.view.layer.bounds;
#endif
    
    [self.view.layer addSublayer:_captureLayer];
    UIButton *button = [[UIButton alloc]initWithFrame:CGRectMake(self.view.bounds.size.width/2-25, self.view.bounds.size.height-100, 50, 50)];
    //[button setBackgroundImage:[UIImage imageNamed:@"eye.png"] forState:UIControlStateNormal];
    [button setTitle:@"取消" forState:UIControlStateNormal];
    [button addTarget:self action:@selector(back) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:button];
}

-(void)back{
    [self dismissViewControllerAnimated:true completion:nil];
}
- (void)captureOutput:(AVCaptureOutput *)captureOutput     didOutputMetadataObjects:(NSArray *)metadataObjects     fromConnection:(AVCaptureConnection *)connection
{
    [_captureLayer removeFromSuperlayer];
    [_session stopRunning];
    AudioServicesPlaySystemSound(kSystemSoundID_Vibrate);
    if (metadataObjects.count > 0) {
        AVMetadataMachineReadableCodeObject * metadataObject = [metadataObjects objectAtIndex : 0 ];
        self.callback(@{@"result":@"true",@"data":metadataObject.stringValue});
        [self dismissViewControllerAnimated:true completion:nil];
    }
}
-(void)viewWillAppear:(BOOL)animated{
    [_session startRunning];
}
- (void)dealloc {
    [_captureLayer removeFromSuperlayer];
}

@end
