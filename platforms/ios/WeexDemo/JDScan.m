//
//  JDScan.m
//  WeexDemo
//
//  Created by 尹路佳 on 2019/4/15.
//  Copyright © 2019 taobao. All rights reserved.
//

#import "JDScan.h"
#import "JDScanViewController.h"

@implementation JDScanModule
@synthesize weexInstance;

WX_EXPORT_METHOD(@selector(scan:))

-(void)scan:(CallBlock)callback{
    // 下面这个是我定义的扫描二维码的原生类
    JDScanViewController *scanVC = [[JDScanViewController alloc]init];
    // 将回调函数传递给scanVC 视图控制器,如果扫描到二维码将值传递给此闭包函数
    scanVC.callback = callback;
    [self.weexInstance.viewController.navigationController     presentViewController:scanVC animated:true completion:nil];
}
@end
