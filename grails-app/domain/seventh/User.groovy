package seventh

class User {

    static hasMany = [pokemons: Pokemon]

    String name
    String password
    Date birthday

    User(String name, String password, Date birthday) {
        this.name = name
        this.password = password
        this.birthday = birthday
    }
}

