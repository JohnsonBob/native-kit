//
//  ViewController.swift
//  AMap-IOS-Kit
//
//  Created by kain on 2018/4/5.
//  Copyright © 2018年 kain. All rights reserved.
//

import UIKit

class ViewController: UIViewController, MAMapViewDelegate {
    private var amap: MAMapView?
    private var amapOptions: AMapOptions?

    override func viewDidLoad() {
        super.viewDidLoad()
        initAMapServices()
    }

    // TODO: 初始化地图
    private func initAMapServices() {
        AMapServices.shared().apiKey = Bundle.main.infoDictionary!["AMapServices"] as! String
        AMapServices.shared().enableHTTPS = true
        amap = MAMapView(frame: view.bounds)
        amap!.delegate = self
        amapOptions = AMapOptions(amap: amap!)
        view.addSubview(amap!)
    }

    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

