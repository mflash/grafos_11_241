import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Map;
import java.util.HashMap;

public class IndexMinHeap<Key, Value extends Comparable<Value>> {

  // Armazena um valor e a posição da chave correspondente no vetor
  private class ValuePos {
    public ValuePos(Value v, int p) { this.value = v; this.pos =p; }
    public Value value;
    public int pos;
  }

  private Key[] pq; // itens armazenados nas posic. 1 a n
  private Map<Key, ValuePos> dic;
  private int n; // qtd de itens

  /**
   * Inicializa uma heap vazia com determinada capacidade
   *
   * @param initCapacity a capacidade inicial deste heap
   */
  public IndexMinHeap(int initCapacity) {
    pq = (Key[]) new Object[initCapacity + 1];
    dic = new HashMap<>();
    n = 0;
  }

  /**
   * Inicializa um heap vazio
   */
  public IndexMinHeap() {
    this(1);
  }

  /**
   * Inicializa um heap a partir do array de chaves e valores
   * <p>
   * Tempo O(n) - n sendo o total de chaves
   *
   * @param keys o array de chaves
   */
  public IndexMinHeap(Key[] keys, Value[] values) {
    n = keys.length;
    pq = (Key[]) new Object[keys.length + 1];
    dic = new HashMap<>();
    for (int i = 0; i < n; i++) {
      pq[i + 1] = keys[i];
      dic.put(keys[i], new ValuePos(values[i],i+1));
    }
    for (int k = n / 2; k >= 1; k--)
      sink(k);
  }

  /**
   * Retorna true se heap estiver vazio
   *
   * @return {@code true} se heap estiver vazio
   *         {@code false} caso contrário
   */
  public boolean isEmpty() {
    return n == 0;
  }

  /**
   * Retorna total de chaves neste heap
   *
   * @return o total de chaves neste heap
   */
  public int size() {
    return n;
  }

  /**
   * REtorna a menor chave neste heap
   *
   * @return menor chave neste heap
   * @throws NoSuchElementException se o heap estiver vazio
   */
  public Key min() {
    if (isEmpty())
      throw new NoSuchElementException("Heap vazio!");
    return pq[1];
  }

  // Método auxiliar para dobrar o tamanho do heap
  private void resize(int capacity) {
    assert capacity > n;
    Key[] temp = (Key[]) new Object[capacity];
    Map<Key, ValuePos> newdic = new HashMap<>();
    for (int i = 1; i <= n; i++) {
      temp[i] = pq[i];
      newdic.put(pq[i], dic.get(pq[i]));
    }
    pq = temp;
    dic = newdic;
  }

  /**
   * Adiciona uma nova chave a este heap
   *
   * @param x a chave a ser adicionada ao heap
   */
  public void insert(Key x, Value v) {
    // duplica o tamanho do array se necessário
    if (n == pq.length - 1)
      resize(2 * pq.length);

    // adiciona x e faz swim para manter propriedade de ordem parcial
    pq[++n] = x;
    dic.put(x,new ValuePos(v, n));
    swim(n);
  }

  /**
   * Remove e retorna a menor chave neste heap
   *
   * @return menor chave no heap
   * @throws NoSuchElementException se o heap estiver vazio
   */
  public Key delMin() {
    if (isEmpty())
      throw new NoSuchElementException("Heap vazio!");
    Key min = pq[1];
    exch(1, n--);
    sink(1);
    pq[n + 1] = null; // para ajudar a garbage collection
    dic.remove(min);
    if ((n > 0) && (n == (pq.length - 1) / 4))
      resize(pq.length / 2);
    return min;
  }

  public boolean contains(Key k) {
    return dic.containsKey(k);
  }

  /**
   * Diminui o valor associado à chave especificada
   *
   * @param  k a chave cujo valor deve ser reduzido
   * @param  v o novo valor a ser associado à chave
   * @throws NoSuchElementException chave inexistente
  */
  public void decreaseValue(Key k, Value v) {
    if (!contains(k)) throw new NoSuchElementException("Chave não existe");
    ValuePos vp = dic.get(k);
    vp.value = v;
    swim(vp.pos);
  }

  /***************************************************************************
   * Métodos auxiliares
   ***************************************************************************/

  private void swim(int k) {
    while (k > 1 && greater(k / 2, k)) {
      exch(k, k / 2);
      k = k / 2;
    }
  }

  private void sink(int k) {
    while (2 * k <= n) {
      int j = 2 * k;
      if (j < n && greater(j, j + 1))
        j++;
      if (!greater(k, j))
        break;
      exch(k, j);
      k = j;
    }
  }

  /***************************************************************************
   * Métodos auxiliares para comparação e troca
   ***************************************************************************/
  private boolean greater(int i, int j) {
    ValuePos vp1 = dic.get(pq[i]);
    ValuePos vp2 = dic.get(pq[j]);
    return vp1.value.compareTo(vp2.value) >  0;
    //return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
  }

  private void exch(int i, int j) {
    ValuePos vp1 = dic.get(pq[i]);
    ValuePos vp2 = dic.get(pq[j]);
    Key swap = pq[i];
    pq[i] = pq[j];
    pq[j] = swap;
    vp1.pos = j;
    vp2.pos = i;
  }

  public static void main(String[] args) {
    IndexMinHeap<String, Double> h = new IndexMinHeap<>(10);
    h.insert("A", 5.0);
    h.insert("X", 2.0);
    h.insert("G", 12.0);
    h.insert("O", 7.0);
    h.insert("F", 1.0);
    h.insert("L", 0.0);

    h.decreaseValue("G", -2.0);

    while (h.size() > 0) {
      System.out.println(h.delMin());
    }
  }
}
