package ff.literalura.service;

public interface IConvertirDatos {
    <T> T obetenerDatos(String json, Class<T> clase);
}
