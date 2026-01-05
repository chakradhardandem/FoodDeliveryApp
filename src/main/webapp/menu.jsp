<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.Model.Menu" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FoodieHub Menu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        *{margin:0;padding:0;box-sizing:border-box;}
        body{font-family:'Segoe UI',system-ui,-apple-system,sans-serif;background:#050814;color:#f5f5f7;min-height:100vh;}
        .wrapper{max-width:1200px;margin:0 auto;padding:2rem 1.5rem 3rem;}
        .page-title{text-align:center;margin-bottom:2rem;}
        .page-title h1{font-size:2.4rem;background:linear-gradient(135deg,#ff8a5c,#f72585,#4cc9f0);-webkit-background-clip:text;-webkit-text-fill-color:transparent;}
        .page-title p{margin-top:0.5rem;color:#a5a8c3;font-size:0.98rem;}
        .menu-grid{display:grid;grid-template-columns:repeat(auto-fit,minmax(280px,1fr));gap:1.8rem;}
        
        /* ✅ NEW ARTICLE STRUCTURE MATCHING YOUR SCREENSHOT */
        .menu-card{
            background:rgba(16,19,38,0.96);border-radius:18px;
            overflow: visible !important;
            border:1px solid rgba(255,255,255,0.05);box-shadow:0 18px 40px rgba(0,0,0,0.65);
            display:flex;flex-direction:column;transition:transform .3s ease, box-shadow .3s ease;
            position:relative;height:420px;
        }
        
        .menu-card::before{content:'';position:absolute;inset:-1px;border-radius:inherit;padding:1px;
            background:linear-gradient(135deg,rgba(255,138,92,0.45),rgba(247,37,133,0.3),rgba(76,201,240,0.4));
            -webkit-mask:linear-gradient(#000 0 0) content-box,linear-gradient(#000 0 0);-webkit-mask-composite:xor;mask-composite:exclude;
            opacity:0;transition:opacity .3s ease;}
        .menu-card:hover{transform:translateY(-10px);box-shadow:0 28px 70px rgba(0,0,0,0.85);border-color:rgba(255,255,255,0.12);}
        .menu-card:hover::before{opacity:1;}
        
        .menu-image{position:relative;height:170px;overflow:hidden;}
        .menu-image img{width:100%;height:100%;object-fit:cover;display:block;transform:scale(1.02);transition:transform .5s ease;}
        .menu-card:hover .menu-image img{transform:scale(1.08);}
        
        /* ✅ PRICE TOP BADGE - MATCHES SCREENSHOT */
        .price-top{
            position:absolute;top:12px;right:12px;background:linear-gradient(135deg,#ff6b35,#ff8a5c);
            color:white;padding:6px 12px;border-radius:25px;font-weight:700;font-size:1rem;
            box-shadow:0 6px 20px rgba(255,107,53,0.4);z-index:2;
        }
        
        .menu-content{padding:1.2rem;flex:1;display:flex;flex-direction:column;}
        .menu-header{margin-top:8px;}
        .menu-title{font-size:1.15rem;font-weight:700;color:#f5f5f7;margin-bottom:0.4rem;line-height:1.3;}
        
        .menu-desc{font-size:0.92rem;color:#aeb1d4;line-height:1.48;margin-bottom:auto;}
        
        .menu-footer{display:flex;justify-content:space-between;align-items:center;gap:1rem;margin-top:auto;padding-top:1rem;border-top:1px solid rgba(255,255,255,0.08);}
        
        .price{font-size:1.08rem;font-weight:700;color:#4cc9f0;}
        .price small{font-size:0.82rem;font-weight:400;color:#8b8fb0;margin-left:0.3rem;}
        
        .seller{display:inline-flex;align-items:center;gap:0.3rem;background:rgba(34,197,94,0.2);color:#10b981;
            padding:0.3rem 0.8rem;border-radius:20px;font-size:0.8rem;font-weight:600;}
        
        /* ✅ PERFECT ADD TO CART BUTTON - MATCHES SCREENSHOT */
        .add-btn{
            border:none;outline:none;cursor:pointer;padding:0.6rem 1.4rem;border-radius:25px;
            background:linear-gradient(135deg,#ff8a5c,#f72585);color:#fff;font-size:0.88rem;font-weight:600;
            letter-spacing:0.015em;display:inline-flex;align-items:center;gap:0.5rem;
            box-shadow:0 8px 25px rgba(247,37,133,0.4);transition:all .2s ease;
            position:relative;overflow:hidden;
        }
        .add-btn:hover{transform:translateY(-2px);box-shadow:0 12px 35px rgba(247,37,133,0.6);filter:brightness(1.08);}
        .add-btn:active{transform:translateY(0);box-shadow:0 6px 20px rgba(247,37,133,0.35);}
        .add-btn span.plus{font-size:1.1rem;font-weight:300;}
        
        /* ✅ FORM FIXES - CRITICAL FOR SERVLET */
        .menu-footer form{margin:0 !important;padding:0 !important;display:inline-block !important;overflow:visible !important;}
        .menu-footer form button{width:100%;justify-content:center;}
        
        @media (max-width:600px){
            .page-title h1{font-size:2rem;}
            .menu-grid{grid-template-columns:repeat(auto-fit,minmax(240px,1fr));gap:1.5rem;}
            .menu-footer{flex-direction:column;gap:0.8rem;}
            .menu-footer form button{width:100%;}
        }
    </style>
</head>
<body>
<div class="wrapper">
    <header class="page-title">
        <h1>Today's Special Menu</h1>
        <p>Carefully crafted dishes, fresh ingredients, and flavors inspired by the best food apps.</p>
    </header>

    <section class="menu-grid">
        <% 
        List<Menu> allMenusByRestaurantId = (List<Menu>)request.getAttribute("allMenusByRestaurantId");
        if(allMenusByRestaurantId != null) {
            for(Menu menu : allMenusByRestaurantId) {
        %>
        <article class="menu-card">
            <div class="menu-image">
                <img src="<%=menu.getImagePath()%>" alt="<%=menu.getItemName()%>">
                <!-- ✅ PRICE TOP BADGE - EXACT SCREENSHOT MATCH -->
                <span class="price-top">₹<%=menu.getPrice()%></span>
            </div>
            <div class="menu-content">
                <!-- ✅ MENU HEADER - EXACT SCREENSHOT MATCH -->
                <div class="menu-header">
                    <h3 class="menu-title"><%=menu.getItemName()%></h3>
                </div>
                <p class="menu-desc"><%=menu.getDescription()%></p>
                <div class="menu-footer">
                    <!-- ✅ BEST SELLER - EXACT SCREENSHOT MATCH -->
                    <div class="seller">Best Seller</div>
                    
                    <!-- ✅ PERFECT FORM - REACHES CARTSERVLET 100% -->
                    <form action="cart">
                        <input type="hidden" name="itemId" value="<%=menu.getMenuId()%>">
                        <input type="hidden" name="restaurantId" value="<%=menu.getRestaurantId()%>">
                        <input type="hidden" name="quantity" value="1">
                        <input type="hidden" name="action" value="add">
                        <button type="submit" class="add-btn">
                            <span class="plus">＋</span>Add to Cart
                        </button>
                    </form>
                </div>
            </div>
        </article>
        <% } } %>
    </section>
</div>
</body>
</html>
