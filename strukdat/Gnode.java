package strukdat;

//public class generic node untuk data berupa pasangan key dan value
public class Gnode<K,V> {
    K key; //key ada bil bulat
    V data; //object data dari sebuah class
    Gnode<K,V> llink; //left link
    Gnode<K,V> rlink; //right link

    //constructor
    public Gnode(K k, V data) {
        this.key = k;
        this.data = data;
        llink = null;
        rlink = null;
    }

    @Override //toString dari class String
    public String toString() {
        return (key.toString() + ":" + data.toString() + " ");
    }

    
}