$(document).ready(function(){
    //Affichage du champ de saisie de mot de passe
    $('input[name^="is_"][type="checkbox"]').change(function(){
        var others = $(this).parent().find('input').not('[type="checkbox"]').not('[type="radio"]');
        others.toggleClass('hidden');     
    });
});