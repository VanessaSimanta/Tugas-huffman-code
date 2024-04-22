package strukdat;
/* Hashing dengan Double Hashing untuk mengatasi collision */

public class DoubleHashing<K> extends Hashing<K>{
    
    public DoubleHashing(int capacity) {
        super(capacity);
    }
    
    @Override
    public void put(K key) {
		HashNode<K> N = new HashNode<K>(key);
        
        int theKey = convertToNumber(key) % table.maxSize();
        int angka = 1 + (convertToNumber(key) % 7);
        int curKey= theKey;
        int i = 1;
        int keyBaru = theKey;

        
		while (isCollision(curKey) && i <= table.maxSize()) {
            curKey = (keyBaru - angka * i) ;
            System.out.println("the key : " + theKey);
            System.out.println("dikurang ama : " + angka * i);
            System.out.println("hasil : " + curKey);
            if (curKey < 0) {
                System.out.println("dibawah nol \n");
                keyBaru = theKey += table.maxSize();
                curKey = (keyBaru - angka * i) ; 
                System.out.println("the key : " + keyBaru);
                System.out.println("dikurang ama : " + angka * i);
                System.out.println("i : " + i);
                System.out.println("hasil : " + curKey);
            }

            i++;
        }
        
		if(!isCollision(curKey)) {
            table.set(curKey,N);
            incSize();
        }
        else System.out.println("Table is full!");
	}
}
