import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: WishViewModel
    let sdk = SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory())
    
//   // как можно?
    
    var body: some View {
        NavigationView {
               listView()
              .navigationBarTitle("Your wish list")
//               .navigationBarItems(trailing:
//                   Button("Reload") {
//                   self.viewModel.
//               })
        }.onAppear {
            viewModel.getWishes(sdk)
        }
       }
    
    private func listView() -> AnyView {
             viewModel.wishes {
                 AnyView(List(wishes) { wish in
                WishView(wish: wish)
            })
//             case .loading:
//                 return AnyView(Text("Loading...").multilineTextAlignment(.center))
//             case .result(let wishes):
//                 return AnyView(List(wishes) { wish in
//                     WishView(wish: wish)
//                 })
//             case .error(let description):
//                 return AnyView(Text(description).multilineTextAlignment(.center))
//             }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    
    static var previews: some View {
		ContentView()
	}
}
