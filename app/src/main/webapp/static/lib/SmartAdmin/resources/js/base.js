/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(".side-menu-level1").click(function() {

$(".collapsed").toggleClass("collapsed"); 

});
//
//$("#clicked").click(function() {
//
////  $(".clicked").toggleClass("accordion-heading side-menu-level1 normal");    
//// $( this ).toggleClass("clicked");
//$(this).attr('id', '');
//$(this).attr('id', 'normal');
//
//});


function startTime()
{
    var today=new Date();
    var h=today.getHours();
    var m=today.getMinutes();
    var s=today.getSeconds();
    // add a zero in front of numbers<10
    m=checkTime(m);
    s=checkTime(s);
    document.getElementById('txt').innerHTML=h+":"+m+":"+s;
    t=setTimeout(function(){
        startTime()
        },500);
}

function checkTime(i)
{
    if (i<10)
    {
        i="0" + i;
    }
    return i;
}


function resize()
{
    var w=window.innerWidth;
    
    
    if(w>995)
    {
        $("#side-menu").animate({
            marginLeft: 0
        }, 50);
        $("#pull").animate({
            marginLeft: 0
        },50);
        document.getElementById('main-container').style.width  = (w-250)+"px"; 
        document.getElementById("pull").innerHTML="<i class=\"icon-chevron-sign-left \"></i>";
    }
    else
    {
        $("#side-menu").animate({
            marginLeft: -220
        }, 0);
        $("#pull").animate({
            marginLeft: -220
        }, 0);
        document.getElementById('main-container').style.width  = (w-30)+"px";
        document.getElementById("pull").innerHTML="<i class=\"icon-chevron-sign-right \"></i>";
    }
    
    //    adjusting the menu hight equl to content height.

//    if(document.getElementById('main-container').style.height > document.getElementById('side-menu').style.height )
//        {
//            //document.getElementById('side-menu').style.height=document.getElementById('main-container').style.height;
//            alert("I am an alert box!");
//        }
//        var h1=$("#side-menu").css('height');
//        alert(h1);



//        document.getElementById('side-menu').style.height=900+"px";
//        var h1 = $("#side-menu").css('height');
        document.getElementById('side-menu').style.height = $("#main-container").css('height');
//        if(h2>h1)
//        {
//            document.getElementById('side-menu').style.height=h2;
//        }
        
        
        
        
            
}

//function resize()
//{
//    var w=window.innerWidth;
//
//    document.getElementById('out').innerHTML = w; 
//
//    if(w>995)
//    {
//    $("#side-menu").animate({ marginLeft: 0 }, 50);
//    $("#pull").animate({ marginLeft: 0 },50);
//    document.getElementById('test').style.width  = (w-250)+"px"; 
//   // document.getElementById('side-menu').style.position= 'absolute';
////   $("a.brand").css("margin-left", "-10px");
////   $(".content").css("margin-top", "70px");
//    }
//    else
//    {
//        //document.getElementById('side-menu').style.visibility='hidden';
//        $("#side-menu").animate({ marginLeft: -215 }, 0);
////        $("#pull").animate({ marginLeft: -215 }, 0);
//        document.getElementById('test').style.width  = (w-50)+"px";
////        $("a.brand").css("margin-left", "30px");
//        $("div.navbar-inner div.container-fluid span").css("margin-right", "50px");
////           $(".content").css("margin-top", "80px");
//    }
//    
//    
//
//        if(document.getElementById('test').style.height > document.getElementById('side-menu').style.height )
//        {
//            document.getElementById('side-menu').style.height=document.getElementById('test').style.height;
//            
//        }
//}




function slideIn() {
    var w=window.innerWidth;
    // slide the wrapper to the right to show the previous panel at the set speed. Then set the nav display on completion of animation.
    $("#side-menu").animate({
        marginLeft: 0
    },100);
    $("#pull").animate({
        marginLeft: 0
    },100);
        
    //  document.getElementById('side-menu').style.position= 'absolute';
    document.getElementById("pull").innerHTML="<i class=\"icon-chevron-sign-left \"></i>";
              
              
    if(w>995)
    {
        document.getElementById('main-container').style.width  = (w-250)+"px"; 
    }
       
redraw();       
}

function slideOut() {
    var w=window.innerWidth;
    // slide the wrapper to the right to show the previous panel at the set speed. Then set the nav display on completion of animation.
    $("#side-menu").animate({
        marginLeft: -220
    }, 100);
    $("#pull").animate({
        marginLeft: -220
    }, 100);
    document.getElementById('main-container').style.width  = (w-30)+"px";
    // document.getElementById('side-menu').style.position='inherit';
                                     
    document.getElementById("pull").innerHTML="<i class=\"icon-chevron-sign-right \"></i>";
    
    redraw();
}
        
//        $(window).resize(function() {
//    // Do something
//    //var height = $(window).height();
//var width = $(window).width();
//
//    if(width>995){if(w>=995){$("#side-menu").animate({ marginLeft: 0 }, 100);}}
//});


function  colapse()
{
    var m = document.getElementById('side-menu').style.marginLeft;
       
    if(m=="0px")
    {
        slideOut();
    }else
    {
        slideIn();
    }
    
}

