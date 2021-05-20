package juegos;

public enum Generos {
    JRPG("Rol"),
    MMORPG("Rol multijugador masivo"),
    FPS("Shooter primera persona"),
    MOBA("Moba"),
    PLATAFORMAS("Plataformas");

    private String descripcion;

    Generos(String descirpcion) {
        this.descripcion = descirpcion;
    }

}
