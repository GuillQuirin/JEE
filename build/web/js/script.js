$(document).ready(function(){
    //Affichage du champ de saisie de mot de passe
    $('input[name^="is_"]').change(function(){
       $(this).parent().find('input[type="text"], input[type="date"], input[type="password"]').toggleClass('hidden');
       console.log($(this).parent().find('input[type="password"]').html());
        /*if($(this).parent().find('input').attr('required'))
           $(this).parent().find('input[type="text"], input[type="date"], input[type="password"]').removeAttr('required');
        else
           $(this).parent().find('input[type="text"], input[type="date"], input[type="password"]').attr('required');
    */});
})