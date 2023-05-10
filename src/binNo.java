public class binNo<T extends Comparable<T>> {

    private T conteudo;
    private binNo<T> noEsq;
    private binNo<T> noDir;

    public T getConteudo() {
        return conteudo;
    }

    public void setConteudo(T conteudo) {
        this.conteudo = conteudo;
    }

    public binNo<T> getNoEsq() {
        return noEsq;
    }

    public void setNoEsq(binNo<T> noEsq) {
        this.noEsq = noEsq;
    }

    public binNo<T> getNoDir() {
        return noDir;
    }

    public void setNoDir(binNo<T> noDir) {
        this.noDir = noDir;
    }

    public binNo(T conteudo) {
        this.conteudo = conteudo;
        noEsq = noDir = null; //inicializa os nós em nulo
    }

    public binNo() {

    }

    @Override
    public String toString() {
        return "binNo{" +
               "conteudo=" + conteudo +
        "}";
    }

   
}