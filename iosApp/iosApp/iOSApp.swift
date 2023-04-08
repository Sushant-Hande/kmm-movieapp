//
//  MovieScreen.swift
//  iosApp
//
//  Created by Sushant Hande on 08/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

@main
struct iOSApp: App {
    
    let movieSDK = MovieSDK()
    
	var body: some Scene {
		WindowGroup {
            MovieScreen(viewModel: .init(movieSDK: movieSDK))
		}
	}
}
