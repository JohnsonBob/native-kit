//
//  ViewController.swift
//  AMap-IOS-Kit
//
//  Created by kain on 2018/4/5.
//  Copyright © 2018年 kain. All rights reserved.
//

import UIKit
import RxSwift

class ViewController: UIViewController, MAMapViewDelegate {
    private var amap: MAMapView?

    override func viewDidLoad() {
        super.viewDidLoad()
        setAMap()
        showPositionDot()
        // updatePositionDot()
    }

    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
    }

    // TODO: 初始化地图
    private func setAMap() {
        AMapServices.shared().apiKey = Bundle.main.infoDictionary!["AMapServices"] as! String
        AMapServices.shared().enableHTTPS = true
        amap = MAMapView(frame: view.bounds)
        amap!.delegate = self
        view.addSubview(amap!)
    }

    // TODO: 显示地图定位蓝点
    private func showPositionDot() {
        amap!.showsUserLocation = true
        amap!.userTrackingMode = .follow
    }

    // TODO: 定位点更新
    private func updatePositionDot() {
        let dot = MAUserLocationRepresentation()
        // TODO:精度圈是否显示
        dot.showsAccuracyRing = false
        // TODO:是否显示蓝点方向指向
        dot.showsHeadingIndicator = false
        // TODO:调整精度圈填充颜色
        dot.fillColor = UIColor.brown
        // TODO:调整精度圈边线颜色
        dot.strokeColor = UIColor.blue
        // TODO:调整精度圈边线宽度
        dot.lineWidth = 2
        // TODO:精度圈是否显示律动效果
        dot.enablePulseAnnimation = false
        // TODO:调整定位蓝点的背景颜色
        dot.locationDotBgColor = UIColor.green
        // TODO:调整定位蓝点的颜色
        dot.locationDotFillColor = UIColor.gray
        // TODO:调整定位蓝点的图标
        dot.image = UIImage(named: "mark_bs")
        amap!.update(dot)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

