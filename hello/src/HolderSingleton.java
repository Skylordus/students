/**
 * Created by Yerlan on 07.03.2017.
 */
public class HolderSingleton {
    //lazy thread-safe singleton
    private HolderSingleton(){}

    private static class SingletonHolder {
        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
            return SingletonHolder.INSTANCE;
    }
}

