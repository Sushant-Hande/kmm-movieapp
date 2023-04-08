//
//  MovieScreen.swift
//  iosApp
//
//  Created by Sushant Hande on 08/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MovieScreen: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        NavigationView{

            VStack{
                ScrollView{
                    updateMovieUI()
                }
                .onAppear {
                    viewModel.getPopularMovies(forceReload: true)
                }
            }
            .navigationTitle("Movie App")
            .foregroundColor(SwiftUI.Color.black)
            .navigationBarTitleDisplayMode(.inline)
        }
    }
    
    
    func updateMovieUI() -> AnyView {
        switch viewModel.launches{
        case .loading:
            return AnyView(Text("Loading"))
            
        case .result(let resultArray):
            let data = resultArray
            let columns = [GridItem(.flexible()), GridItem(.flexible())]
            
            return AnyView(LazyVGrid(columns: columns, spacing: 20) {
                ForEach(data, id: \.self) { item in
                    MovieItem(image: item.getImagePath())
                }
            }
                .padding(.horizontal))
            
            
        case .error(let result):
            return AnyView(Text("\(result)"))
        }
    }
}

struct MovieItem : View {
    var image: String
    var body: some View {
        AsyncImage(url: URL(string: image))
            .frame(width: 150, height: 200, alignment: .bottom)
            .cornerRadius(8)
    }
}

extension MovieScreen {
    enum LoadableLaunches {
        case loading
        case result([Result])
        case error(String)
    }
    
    class ViewModel: ObservableObject {
        let movieSDK : MovieSDK
        
        @Published var launches = LoadableLaunches.loading
        
        init(movieSDK: MovieSDK) {
            self.movieSDK = movieSDK
        }
        
        func getPopularMovies(forceReload: Bool) {
            self.launches = .loading
            movieSDK.getPopularMovies(completionHandler: { launches, error in
                if let launches = launches {
                    self.launches = .result(launches.results)
                } else {
                    self.launches = .error(error?.localizedDescription ?? "error")
                }
            })
        }
    }
}
