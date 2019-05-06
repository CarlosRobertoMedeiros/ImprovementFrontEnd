var campos = [
    document.querySelector('#data'),
    document.querySelector('#quantidade'),
    document.querySelector('#valor')
];



var tbody = document.querySelector('table tbody');

document.querySelector('.form').addEventListener('submit',function (event){
    
    
    //Exemplo de visualização
    // <tbody>
    //     <tr>
    //         <td>date</td>
    //         <td>quantidade</td>
    //         <td>valor</td>
    //         <td>volume</td>
    //     </tr>  
    // </tbody>  


    //Modifica o comportamento do listener no caso submit 
    event.preventDefault();
    
    var tr = document.createElement('tr');
    campos.forEach(function(campo){
        var td = document.createElement('td');
        td.textContent = campo.value;
        tr.appendChild(td);
    });

    var tdVolume = document.createElement('td');
    tdVolume.textContent = campos[1].value * campos[2].value;

    tr.appendChild(tdVolume);

    tbody.appendChild(tr);
    

    campos[0].value = '';
    campos[1].value = 1;
    campos[2].value = 0;

    campos[0].focus();


});


console.log(campos);