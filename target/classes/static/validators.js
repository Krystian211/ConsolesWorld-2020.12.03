function validateRegisterData(){

    var login=document.forms["register-form"]["login"].value;
    var password=document.forms["register-form"]["password"].value;
    var repeatedPassword=document.forms["register-form"]["repeatedPassword"].value;
    var firstName=document.forms["register-form"]["firstName"].value;
    var lastName=document.forms["register-form"]["lastName"].value;
    var email=document.forms["register-form"]["email"].value;


    var loginRegex=new RegExp(/.{3,}/);
    var passwordRegex=new RegExp(/.{5,}/);
    var firstNameRegex=new RegExp(/[A-ZĄĆĘŁŃÓŚŻŹ]+[a-ząćęłńóśżź]{2,}/);
    var lastNameRegex=new RegExp(/[A-ZĄĆĘŁŃÓŚŻŹ]+[a-ząćęłńóśżź-]{2,}/);
    var emailRegex=new RegExp(/.+@[a-zA-Z0-9]{2,}\.[a-zA-Z]{2,3}/);

    var flag=true;
    if(!loginRegex.test(login)){
        document.forms["register-form"]["login"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["register-form"]["login"].style.background="white";
    }
    if(!firstNameRegex.test(firstName)){
        document.forms["register-form"]["firstName"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["register-form"]["firstName"].style.background="white";
    }
    if(!lastNameRegex.test(lastName)){
        document.forms["register-form"]["lastName"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["register-form"]["lastName"].style.background="white";
    }
    if(!emailRegex.test(email)){
        document.forms["register-form"]["email"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["register-form"]["email"].style.background="white";
    }
    if(!passwordRegex.test(password)){
        document.forms["register-form"]["password"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["register-form"]["password"].style.background="white";
    }
    if(repeatedPassword!=password||repeatedPassword==""){
        document.forms["register-form"]["repeatedPassword"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["register-form"]["repeatedPassword"].style.background="white";
    }

    return flag;
}

function validateEditAccountData(){

    var login=document.forms["edit-account-form"]["login"].value;
    var oldPassword=document.forms["edit-account-form"]["oldPassword"].value;
    var newPassword=document.forms["edit-account-form"]["newPassword"].value;
    var repeatedNewPassword=document.forms["edit-account-form"]["repeatedNewPassword"].value;
    var firstName=document.forms["edit-account-form"]["firstName"].value;
    var lastName=document.forms["edit-account-form"]["lastName"].value;
    var email=document.forms["edit-account-form"]["email"].value;

    var loginRegex=new RegExp(/.{3,}/);
    var passwordRegex=new RegExp(/.{5,}/);
    var firstNameRegex=new RegExp(/[A-ZĄĆĘŁŃÓŚŻŹ]+[a-ząćęłńóśżź]{2,}/);
    var lastNameRegex=new RegExp(/[A-ZĄĆĘŁŃÓŚŻŹ]+[a-ząćęłńóśżź-]{2,}/);
    var emailRegex=new RegExp(/.+@[a-zA-Z0-9]{2,}\.[a-zA-Z]{2,3}/);

    var flag=true;
    if(!loginRegex.test(login)){
        document.forms["edit-account-form"]["login"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["edit-account-form"]["login"].style.background="white";
    }
    if(!firstNameRegex.test(firstName)){
        document.forms["edit-account-form"]["firstName"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["edit-account-form"]["firstName"].style.background="white";
    }
    if(!lastNameRegex.test(lastName)){
        document.forms["edit-account-form"]["lastName"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["edit-account-form"]["lastName"].style.background="white";
    }
    if(!emailRegex.test(email)){
        document.forms["edit-account-form"]["email"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["edit-account-form"]["email"].style.background="white";
    }
    if(!passwordRegex.test(oldPassword)){
        document.forms["edit-account-form"]["oldPassword"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["edit-account-form"]["oldPassword"].style.background="white";
    }
    if(!((newPassword=="")&&(repeatedNewPassword==""))){
        if(!passwordRegex.test(newPassword)){
            document.forms["edit-account-form"]["newPassword"].style.background="#f5aeae";
            flag=false;
        }else{
            document.forms["edit-account-form"]["newPassword"].style.background="white";
        }
        if(repeatedNewPassword!=newPassword){
            document.forms["edit-account-form"]["repeatedNewPassword"].style.background="#f5aeae";
            flag=false;
        }else{
            document.forms["edit-account-form"]["repeatedNewPassword"].style.background="white";
        }
    }else{
        document.forms["edit-account-form"]["newPassword"].style.background="white";
        document.forms["edit-account-form"]["repeatedNewPassword"].style.background="white";
    }
    return flag;
}

function validateProductData(){

    var name=document.forms["add-new-product-form"]["name"].value;
    var brand=document.forms["add-new-product-form"]["brand"].value;
    var manufacturerCode=document.forms["add-new-product-form"]["manufacturerCode"].value;

    var nameRegex=new RegExp(/[A-ZĄĆĘŁŃÓŚŻŹ]+.+/);
    var brandRegex=new RegExp(/[A-ZĄĆĘŁŃÓŚŻŹ]+.+/);
    var manufacturerCodeRegex=new RegExp(/.{3,}/);

    var flag=true;
    if(!nameRegex.test(name)){
        document.forms["add-new-product-form"]["name"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["add-new-product-form"]["name"].style.background="white";
    }
    if(!brandRegex.test(brand)){
        document.forms["add-new-product-form"]["brand"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["add-new-product-form"]["brand"].style.background="white";
    }
    if(!manufacturerCodeRegex.test(manufacturerCode)){
        document.forms["add-new-product-form"]["manufacturerCode"].style.background="#f5aeae";
        flag=false;
    }else{
        document.forms["add-new-product-form"]["manufacturerCode"].style.background="white";
    }
    return flag;
}