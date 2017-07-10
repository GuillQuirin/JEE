$(document).ready(function(){
    //Affichage du champ de saisie de mot de passe
    $('input[type="checkbox"][name="pwd_url"]').change(function(){
       $(this).parent().find('input[type="password"]').toggleClass('hidden');
    });
})