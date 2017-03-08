/**
 * Created by Yerlan on 07.03.2017.
 */
public enum EnumSingleton {
    INSTANCE;
    private Object obj = null;

    public Object createInstance() {
        if (obj == null) {
            obj = new Object();
        }
        return obj;
    }
}
