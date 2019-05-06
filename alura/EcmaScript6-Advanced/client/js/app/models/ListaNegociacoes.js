class ListaNegociacoes{
    constructor (){
        this._negociacoes = [];
    }

    //Pela "Regra de Negócio" só podemos adicionar na negociação
    //Por isso não estou implementando outras funções na lista
    adiciona(negociacao){
        this._negociacoes.push(negociacao);
    }

    get negociacoes(){
        //Mando uma cópia das negociações e não a direta
        return [].concat(this._negociacoes);
    }

    //Limpa a lista
    esvazia(){
        this._negociacoes = [];
    }


}