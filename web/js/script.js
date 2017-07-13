$(document).ready(function(){
    //Affichage des champs de saisie cachés
    $('input[name^="is_"][type="checkbox"]').change(function(){
        var others = $(this).parent().parent().find('input').not('[type="checkbox"]').not('[type="radio"]');
        others.toggleClass('hidden');     
    });
    
    //Génération du tableau
    if($("#listUrl")){
        $('#listUrl').DataTable();
    }
    
    //Bouton de copie dans presse-papier
    (function(){
        new Clipboard('#copy');
    })();

});