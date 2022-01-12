import Foundation
import SwiftUI

struct WishRow: View {
    var wish: Wish
    
    var body: some View {
        HStack() {
            Text(wishText)
            Spacer()
            Text("remove").foregroundColor(Color.red)
        }
    }
}

extension WishRow {
    private var wishText: String {
        return wish.text
    }
}
