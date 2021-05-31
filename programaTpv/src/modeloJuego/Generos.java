package modeloJuego;

public enum Generos {
    RPG("Juegos de Rol"),
    MMORPG("Rol multijugador masivo"),
    FPS("Shooter primera persona"),
    AVENTURAS("Aventuras"),
    CASUAL("Casual"),
    PLATAFORMAS("Plataformas"),
    TODOS("Todos");

    private String descripcion;

    Generos(String descirpcion) {
        this.descripcion = descirpcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
