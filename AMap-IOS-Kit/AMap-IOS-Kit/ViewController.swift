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

    override func viewDidLoad() {
        super.viewDidLoad()
        setAMapService()
    }

    private func setAMapService() {
        var dict: NSDictionary?
        if let path = Bundle.main.path(forResource: "Config", ofType: "plist") {
            dict = NSDictionary(contentsOfFile: path)
        }
        if let config = dict {
            AMapServices.shared().apiKey = config["AMapServices"] as? String
            AMapServices.shared().enableHTTPS = true
        }
    }

    private func setAMap() {
        amap = MAMapView(frame: view.bounds)
        amap!.delegate = self
        view.addSubview(amap!)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

