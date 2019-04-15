//
//  JDScanViewController.h
//  WeexDemo
//
//  Created by 尹路佳 on 2019/4/15.
//  Copyright © 2019 taobao. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>
#import <WeexSDK/WeexSDK.h>

@interface JDScanViewController : UIViewController <AVCaptureMetadataOutputObjectsDelegate>

@property(nonatomic,copy)WXModuleCallback callback;

@end
