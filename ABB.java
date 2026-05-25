/*
 * Estrutura de Dados 
 * 
 * Integrantes:
 * Ana Lessa Ferreira - 10732666
 * Caio Vinicius Mussi Trindade - 10735885
 * Julia Oliveira Longhi - 10736801
 * Vitor Kenzo M. Ochida - 10737201
 */

// Classe ABB<T>: manipula a arvore de busca binaria generica.
// Baseada no material de aula, com alguns metodos extras para o Projeto 2.
public class ABB<T extends Comparable<T>> {

    private Node<T> raiz;

    public ABB() {
        raiz = null;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public void setRaiz(Node<T> araiz) {
        raiz = araiz;
    }

    public Node<T> getRaiz() {
        return raiz;
    }

    public Node<T> search(T e) {
        return search(raiz, e);
    }

    private Node<T> search(Node<T> node, T e) {
        if (node == null) {
            return null;
        }

        if (compara(e, node.getValue()) == 0) {
            return node;
        }

        if (compara(e, node.getValue()) < 0) {
            return search(node.getFilhoEsquerdo(), e);
        }

        return search(node.getFilhoDireito(), e);
    }

    // Busca parecida com a de cima, mas contando quantas comparacoes foram feitas.
    public Node<T> searchComComparacoes(T e, int[] comparacoes) {
        Node<T> atual = raiz;

        while (atual != null) {
            comparacoes[0]++;

            int cmp = compara(e, atual.getValue());

            if (cmp == 0) {
                return atual;
            } else if (cmp < 0) {
                atual = atual.getFilhoEsquerdo();
            } else {
                atual = atual.getFilhoDireito();
            }
        }

        return null;
    }

    public T inserir(T valor) {
        try {
            Node<T> novo = new Node<T>(valor);
            raiz = inserir(novo, raiz);
            return valor;
        } catch (Exception e) {
            return null;
        }
    }

    private Node<T> inserir(Node<T> novo, Node<T> atual) {
        if (atual == null) {
            return novo;
        }

        if (compara(novo.getValue(), atual.getValue()) < 0) {
            atual.setFilhoEsquerdo(inserir(novo, atual.getFilhoEsquerdo()));
        } else {
            atual.setFilhoDireito(inserir(novo, atual.getFilhoDireito()));
        }

        return atual;
    }

    public String emOrdem() {
        return emOrdem(raiz);
    }

    public String emOrdem(Node<T> no) {
        if (no == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(emOrdem(no.getFilhoEsquerdo()));
        sb.append(no.getValue()).append("\n");
        sb.append(emOrdem(no.getFilhoDireito()));

        return sb.toString();
    }

    public void emOrdem2() {
        emOrdem2(raiz);
    }

    public void emOrdem2(Node<T> no) {
        if (no != null) {
            emOrdem2(no.getFilhoEsquerdo());
            System.out.println(no.getValue());
            emOrdem2(no.getFilhoDireito());
        }
    }

    public void preOrdem() {
        preOrdem(raiz);
    }

    public void preOrdem(Node<T> no) {
        if (no != null) {
            System.out.print(no.getValue() + "   ");
            preOrdem(no.getFilhoEsquerdo());
            preOrdem(no.getFilhoDireito());
        }
    }

    public void posOrdem() {
        posOrdem(raiz);
    }

    public void posOrdem(Node<T> no) {
        if (no != null) {
            posOrdem(no.getFilhoEsquerdo());
            posOrdem(no.getFilhoDireito());
            System.out.print(no.getValue() + "   ");
        }
    }

    public void emNivel() {
        if (raiz == null) {
            System.out.println("Arvore vazia.");
            return;
        }

        Node<T> noAux;
        LinkedList<Node<T>> fila = new LinkedList<Node<T>>();
        fila.addLast(raiz);

        while (!fila.isEmpty()) {
            noAux = fila.pollFirst();

            if (noAux.getFilhoEsquerdo() != null) {
                fila.addLast(noAux.getFilhoEsquerdo());
            }

            if (noAux.getFilhoDireito() != null) {
                fila.addLast(noAux.getFilhoDireito());
            }

            System.out.print(noAux.getValue() + "   ");
        }
    }

    private int compara(T ob1, T ob2) {
        return ob1.compareTo(ob2);
    }

    public Node<T> getMenor(Node<T> node) {
        if (isEmpty() || node == null) {
            return null;
        }

        if (node.getFilhoEsquerdo() == null) {
            return node;
        }

        return getMenor(node.getFilhoEsquerdo());
    }

    public Node<T> getMaior(Node<T> node) {
        if (isEmpty() || node == null) {
            return null;
        }

        if (node.getFilhoDireito() == null) {
            return node;
        }

        return getMaior(node.getFilhoDireito());
    }

    public Node<T> getMax(Node<T> raiz, Node<T> paiRaiz) {
        if (isEmpty() || raiz == null) {
            return null;
        }

        Node<T> aux;

        if (raiz.getFilhoDireito() == null) {
            aux = raiz;

            if (paiRaiz != null) {
                if (paiRaiz.getFilhoEsquerdo() == raiz) {
                    paiRaiz.setFilhoEsquerdo(raiz.getFilhoEsquerdo());
                } else {
                    paiRaiz.setFilhoDireito(raiz.getFilhoEsquerdo());
                }
            }

            return aux;
        }

        return getMax(raiz.getFilhoDireito(), raiz);
    }

    public boolean eliminar(T e) {
        return eliminar(raiz, null, e);
    }

    private boolean eliminar(Node<T> node, Node<T> paiRaiz, T e) {
        Node<T> aux;

        if (node == null) {
            return false;
        }

        if (compara(e, node.getValue()) == 0) {
            aux = node;

            if (node.getFilhoEsquerdo() == null && node.getFilhoDireito() == null) {
                if (paiRaiz == null) {
                    setRaiz(null);
                } else {
                    if (paiRaiz.getFilhoEsquerdo() != null && compara(paiRaiz.getFilhoEsquerdo().getValue(), e) == 0) {
                        paiRaiz.setFilhoEsquerdo(null);
                    } else if (paiRaiz.getFilhoDireito() != null && compara(paiRaiz.getFilhoDireito().getValue(), e) == 0) {
                        paiRaiz.setFilhoDireito(null);
                    }
                }
            } else if (node.getFilhoDireito() == null) {
                if (paiRaiz != null) {
                    if (paiRaiz.getFilhoEsquerdo() != null && compara(paiRaiz.getFilhoEsquerdo().getValue(), e) == 0) {
                        paiRaiz.setFilhoEsquerdo(node.getFilhoEsquerdo());
                    } else {
                        paiRaiz.setFilhoDireito(node.getFilhoEsquerdo());
                    }
                } else {
                    Node<T> filho = node.getFilhoEsquerdo();
                    node.setValue(filho.getValue());
                    node.setFilhoEsquerdo(filho.getFilhoEsquerdo());
                    node.setFilhoDireito(filho.getFilhoDireito());
                }
            } else if (node.getFilhoEsquerdo() == null) {
                if (paiRaiz != null) {
                    if (paiRaiz.getFilhoEsquerdo() != null && compara(paiRaiz.getFilhoEsquerdo().getValue(), e) == 0) {
                        paiRaiz.setFilhoEsquerdo(node.getFilhoDireito());
                    } else {
                        paiRaiz.setFilhoDireito(node.getFilhoDireito());
                    }
                } else {
                    Node<T> filho = node.getFilhoDireito();
                    node.setValue(filho.getValue());
                    node.setFilhoEsquerdo(filho.getFilhoEsquerdo());
                    node.setFilhoDireito(filho.getFilhoDireito());
                }
            } else {
                aux = getMax(node.getFilhoEsquerdo(), node);
                node.setValue(aux.getValue());
            }

            aux = null;
            return true;
        }

        if (compara(e, node.getValue()) < 0) {
            return eliminar(node.getFilhoEsquerdo(), node, e);
        }

        return eliminar(node.getFilhoDireito(), node, e);
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(Node<T> no) {
        if (no == null) {
            return -1;
        }

        int alturaEsquerda = altura(no.getFilhoEsquerdo());
        int alturaDireita = altura(no.getFilhoDireito());

        return 1 + Math.max(alturaEsquerda, alturaDireita);
    }

    public int totalNos() {
        return totalNos(raiz);
    }

    private int totalNos(Node<T> no) {
        if (no == null) {
            return 0;
        }

        return 1 + totalNos(no.getFilhoEsquerdo()) + totalNos(no.getFilhoDireito());
    }
}
