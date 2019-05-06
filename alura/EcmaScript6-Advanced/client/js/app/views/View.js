class View{

    constructor(elemento){
        this._elemento = elemento;
    };

    //Ainda não existe classe abstrata em Js 
    template(){
        throw new Error("O método template deve ser implementado");
    };
    
    update(model){
        this._elemento.innerHTML = this.template(model);
    };
}