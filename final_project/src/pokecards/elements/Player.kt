package pokecards.elements

class Player {
    private var id: Int
    var name: String
    var cards: Deck

    init {
        id = -1
        name = "unknown"
        cards = Deck()
    }

    fun modify(id:Int, name:String) {
        if (id < 0) throw IllegalArgumentException("Player.modify")
        this.id = id
        this.name = name
    }

    fun show():String {
        if (id == null || name == null) throw NullPointerException("Player.show")
        var res = "name : $name, id : $id"
        return res
    }

}