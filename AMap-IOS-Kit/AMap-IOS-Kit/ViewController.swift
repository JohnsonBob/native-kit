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
    }

    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
    }

    private func setAMap() {
        AMapServices.shared().apiKey = Bundle.main.infoDictionary!["AMapServices"] as! String
        AMapServices.shared().enableHTTPS = true
        amap = MAMapView(frame: view.bounds)
        amap!.delegate = self
        view.addSubview(amap!)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

