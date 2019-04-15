//
//  JDScan.h
//  WeexDemo
//
//  Created by 尹路佳 on 2019/4/15.
//  Copyright © 2019 taobao. All rights reserved.
//

#ifndef JDScan_h
#define JDScan_h

#import <WeexSDK/WeexSDK.h>

typedef void (^CallBlock)(NSDictionary*); // 定义回调函数
@interface JDScanModule :NSObject<WXModuleProtocol>
@end

#endif /* JDScan_h */
