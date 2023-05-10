public class ArvoreBinaria<T extends Comparable<T>> {
    
    private binNo<T> raiz; //inicializando o no raiz

    public ArvoreBinaria(){
        this.raiz = null;
    }

    //insere o novo conteudo 
    public void inserir(T conteudo){
        binNo<T> novoNo = new binNo<>(conteudo);
        raiz = inserir(raiz, novoNo);
    }

    //comparação começando pela raiz para decidir onde o novo no ficara 
    private binNo<T> inserir(binNo<T> atual, binNo<T> novoNo){
        if(atual == null){
            return novoNo;
        } else if(novoNo.getConteudo().compareTo(atual.getConteudo()) < 0){
        //se o novo no for menor que o no ao que ele foi comparado 
            atual.setNoEsq(inserir(atual.getNoDir(), novoNo));
            //ele vai ser colocado a esquerda
        }else{
            atual.setNoDir(inserir(atual.getNoDir(), novoNo));
            //se não,ele sera colocado a direita
        }
        return atual;
    }

    void exibirInOrdem(){
        System.out.println("\n Exibindo InOrdem");
        exibirInOrdem(this.raiz);
    }

    //exibe os valores armazenados em ordem crescente
    private void exibirInOrdem(binNo<T> atual){
        if(atual != null){
        //se o no atual for diferente que null
            exibirInOrdem(atual.getNoEsq()); //visita os nos a esquerda
            System.out.println(atual.getConteudo() + ", "); //exibe o no
            exibirInOrdem(atual.getNoDir()); //visita os nos a direita
        }
    }

    void exibirPosOrdem(){
        System.out.println("\n Exibindo PosOrdem");
        exibirPosOrdem(this.raiz);
    }

    
    private void exibirPosOrdem(binNo<T> atual){
        if(atual != null){
        //se o no atual for diferente de null
            exibirPosOrdem(atual.getNoEsq()); //percorre os nos da esquerda de baixo para cima
            exibirPosOrdem(atual.getNoDir());//percorre os nos da direita de baixo para cima 
            System.out.println(atual.getConteudo() + ", ");
        }
    }

    void exibirPreOrdem(){
        System.out.println("\n Exibindo PreOrdem");
        exibirPreOrdem(this.raiz);
    }

    private void exibirPreOrdem(binNo<T> atual){
        if(atual != null){
        //se o atual for diferente de null
            System.out.println(atual.getConteudo() + ", "); //a raiz é printada primeiro
            exibirPreOrdem(atual.getNoEsq()); //seguida dos nos da esquerda
            exibirInOrdem(atual.getNoDir()); //depois os da direita 
        }
    }

    public void remover(T conteudo){
        try{
            
            //nos auxiliares
            binNo<T> atual = this.raiz;
            binNo<T> pai = null;
            binNo<T> filho = null;
            binNo<T> temp = null;

            //percorre a arvore procurando o conteudo
            while (atual != null && !atual.getConteudo().equals(conteudo)){
            //o loop vai acontecer enquanto o conteudo não ser achado
                pai = atual;
                if(conteudo.compareTo(atual.getConteudo()) < 0){
                //se o conteudo for menor ele vai para esquerda
                    atual = atual.getNoEsq();
                }else {
                    //se for maior vai para direita 
                    atual = atual.getNoDir();
                }
            }

            //se o conteudo não for encontrado
            if(atual == null){
                System.out.println("Conteudo nao encontrado. Bloco Try");
            }

            if(pai == null){
            //se o pai for igual a nulo
                if(atual.getNoDir() == null){
                //se o no da direita for nulo
                    this.raiz = atual.getNoEsq();
                    //ele passa para a direita 
                }else if(atual.getNoEsq() == null){
                //se a esquerda for nula 
                    this.raiz = atual.getNoDir();
                    //ele passa para esquerda
                }else {
                //caso seja uma outra opção
                    for(temp = atual, filho = atual.getNoEsq();
                        filho.getNoDir() != null; //condição de parada
                        temp = filho, filho = filho.getNoEsq() //incremento
                    ){
                        if(filho != atual.getNoEsq()){
                        //se o filho for diferente de atual 
                        //os nos são rearranjados 
                            temp.setNoDir(filho.getNoEsq());
                            filho.setNoEsq(raiz.getNoEsq());
                        }
                    }
                    filho.setNoDir(raiz.getNoDir());
                    raiz = filho;
                }
            //percorre a arvore 
            }else if(atual.getNoDir() == null){ //testa se o no atual a direita é nulo 
                if(pai.getNoEsq() == atual){ //se o no a esquerda do pai for o no atual
                    pai.setNoEsq(atual.getNoEsq()); //o valor do no atual se torna o no esquerdo
                }else { //caso contrário,
                    pai.setNoDir(atual.getNoEsq()); //o valor do no direito se torna igual ao do esquerdo
                }
            }else if(atual.getNoEsq() == null){ //testa se o no atual ao esquerdo é nulo 
                if(pai.getNoEsq() == atual){ //se o no a esquerda for o no ataul 
                    pai.setNoEsq(atual.getNoDir()); //o valor do no atual se torna o no direito
                }else { //caso contrário,
                    pai.setNoDir(atual.getNoDir()); //o valor do no direito permanece o mesmo
                }
            }else{
                for(
                        //percorre todos os filhos 
                        temp = atual, filho = atual.getNoEsq(); //filho é igual ao no atual do lado esquerdo
                        filho.getNoDir() != null; //sai do loop se o filho atual direito for diferente de nulo 
                        temp = filho, filho = filho.getNoDir() //atualiza o tem como filho e o filho como o atual direito
                ){
                    if(filho != atual.getNoEsq()){ //se o filho for diferente do atual esquerdo 
                        temp.setNoDir(filho.getNoEsq()); // atualiza o temp como o filho do no esquerdo
                        filho.setNoEsq(atual.getNoEsq()); //o filho anterior esquerdo ocupa o lugar do anterior
                    }
                    filho.setNoDir(atual.getNoDir()); //o filho anterior direito ocupa o lugar do anterior
                    if(pai.getNoEsq() == atual){ //se o pai for igual ao atual 
                        pai.setNoEsq(filho); //o pai seta o seu proximo esquerdo como filho 
                    }else{ //caso contrário,
                        pai.setNoDir(filho); //o pai seta o seu proximo direito como filho 
                    }
                }
            }
        }catch (NullPointerException erro){
            System.out.println("Conteudo nao encontrado. Bloco Catch");
        }
    }

}
