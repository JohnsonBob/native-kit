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
        showsIndoorMap()
        // showTraffic()
        setLogoCenter()
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

    // TODO:开启室内地图
    private func showsIndoorMap() {
        amap!.isShowsIndoorMap = true
    }

    // TODO:显示交通状态
    private func showTraffic() {
        amap!.isShowTraffic = true
    }

    // TODO:卫星地图 .standardNight=>夜景 .satellite=>卫星
    private func setMapType() {
        amap!.mapType = .standard
    }

    // TODO:地图Logo设置
    private func setLogoCenter() {
        amap!.logoCenter = CGPoint(x: 10, y: 10)
    }

    // TODO:指南针设置
    private func setCompass() {
        amap!.showsCompass = true
        amap!.compassOrigin = CGPoint(x: 10, y: 10)
    }

    // TODO:比例尺设置
    private func setScale() {
        amap!.showsScale = true
        amap!.scaleOrigin = CGPoint(x: 10, y: 10)
    }

    // TODO:缩放手势
    private func setZoomEnabled() {
        amap!.isZoomEnabled = false
    }

    // TODO:地图缩放功能拓展
    private func setZoomLevel() {
        amap!.setZoomLevel(17.5, animated: true)
    }

    // TODO:可以通过手势拖动地图
    private func setScrollEnabled() {
        amap!.isScrollEnabled = false
    }

    // TODO:地图平移式，缩放级别不变
    private func setCenter() {
        amap!.setCenter(CLLocationCoordinate2D(latitude: 39.920816, longitude: 116.373395), animated: true)
    }

    // TODO:禁用或开启旋转手势
    private func setRotateEnabled() {
        amap!.isRotateEnabled = false
    }

    // TODO:旋转扩展设置
    private func setRotationDegree() {
        amap!.setRotationDegree(60, animated: true, duration: 0.5)
    }

    // TODO:倾斜状态设置
    private func setCameraEnabled() {
        amap!.isRotateCameraEnabled = false
    }

    // TODO:倾斜扩展设置
    private func setCameraDegree() {
        amap!.setCameraDegree(30, animated: true, duration: 0.5)
    }

    // TODO:指定屏幕中心点的手势操作
    private func setScreenAnchor() {
        amap!.screenAnchor = CGPoint(x: 0.5, y: 0.8)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

