class DataHelper{

    constructor(){
        throw new Error("DateHelper não pode ser instanciada");
    }
    
    static dataParaTexto(data){
        /*return data.getDate()+'/'+
               (data.getMonth()+1)+'/'+
               data.getFullYear();*/

        /*Interpolação para ES6 = chama-se template String*/
        return `${data.getDate()}/${data.getMonth()+1}/${data.getFullYear()}`;
    };

    static textoParaData(texto){
        console.log(texto);
        if (!/\d{4}-\d{2}-\d{2}/.test(texto))
            throw new Error("A Data deve estar no seguinte formato aaaa-mm-dd");
        return new Date(...texto.split('-').map((item,indice) => item-indice%2)
        
        );
    }

}