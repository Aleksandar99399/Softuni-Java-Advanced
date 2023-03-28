package GenericsLab.ArrayCreator;

import java.lang.reflect.Array;

public class ArrayCreator {

   public static <T> T[] create(int length,T item){
       T []result=(T[]) new  Object[length];

       for (int i = 0; i < result.length; i++) {

           result[i]=item;
       }
       return result;
   }

    public static <T> T[] create(Class<T> clazz, int length, T item) {

        T[] o =(T[]) Array.newInstance(clazz, length);

        for (int i = 0; i < o.length; i++) {

            o[i]=item;
        }
        return o;
    }
}
