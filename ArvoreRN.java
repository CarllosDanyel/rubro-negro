public class ArvoreRN {
    private static final boolean VERMELHO = true;
    private static final boolean PRETO = false;

    private No raiz;

    private class No {
        int chave;
        No esquerda, direita;
        boolean cor;

        public No(int chave, boolean cor) {
            this.chave = chave;
            this.cor = cor;
        }
    }

    private boolean ehVermelho(No no) {
        if (no == null) {
            return false;
        }
        return no.cor == VERMELHO;
    }

    private No rotacionarEsquerda(No no) {
        No novoNo = no.direita;
        no.direita = novoNo.esquerda;
        novoNo.esquerda = no;
        novoNo.cor = no.cor;
        no.cor = VERMELHO;
        return novoNo;
    }

    private No rotacionarDireita(No no) {
        No novoNo = no.esquerda;
        no.esquerda = novoNo.direita;
        novoNo.direita = no;
        novoNo.cor = no.cor;
        no.cor = VERMELHO;
        return novoNo;
    }

    private void inverterCores(No no) {
        no.cor = !no.cor;
        no.esquerda.cor = !no.esquerda.cor;
        no.direita.cor = !no.direita.cor;
    }

    public void inserir(int chave) {
        raiz = inserir(raiz, chave);
        raiz.cor = PRETO;
    }

    private No inserir(No no, int chave) {
        if (no == null) {
            return new No(chave, VERMELHO);
        }

        if (chave < no.chave) {
            no.esquerda = inserir(no.esquerda, chave);
        } else if (chave > no.chave) {
            no.direita = inserir(no.direita, chave);
        } else {
            // Chave já existe na árvore, não faz nada
            return no;
        }

        if (ehVermelho(no.direita) && !ehVermelho(no.esquerda)) {
            no = rotacionarEsquerda(no);
        }
        if (ehVermelho(no.esquerda) && ehVermelho(no.esquerda.esquerda)) {
            no = rotacionarDireita(no);
        }
        if (ehVermelho(no.esquerda) && ehVermelho(no.direita)) {
            inverterCores(no);
        }

        return no;
    }
}