<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.Model.Cart" %>
<%@ page import="com.Model.CartItem" %>

<!DOCTYPE html>
<html>
<head>
    <title>Your Cart</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', -apple-system, BlinkMacSystemFont, sans-serif;
            background: #0a0a0f;
            color: #ffffff;
            min-height: 100vh;
            overflow-x: hidden;
        }

        body::before {
            content: '';
            position: fixed;
            inset: 0;
            background:
                radial-gradient(circle at 20% 80%, rgba(120,119,198,0.3), transparent 50%),
                radial-gradient(circle at 80% 20%, rgba(255,119,198,0.3), transparent 50%),
                radial-gradient(circle at 40% 40%, rgba(120,219,255,0.2), transparent 50%);
            z-index: -1;
            animation: float 20s ease-in-out infinite;
        }

        @keyframes float {
            0%,100% { transform: scale(1) rotate(0deg); }
            50% { transform: scale(1.1) rotate(180deg); }
        }

        h1 {
            text-align: center;
            padding: 2rem 0;
            font-size: 3rem;
            font-weight: 900;
            background: linear-gradient(135deg, #ff6bcb, #43e97b, #667eea);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }

        /* 🛒 CART CONTAINER */
        .cart-container {
            max-width: 900px;
            margin: 2rem auto 4rem;
            padding: 2rem 2.2rem;
            background: rgba(20, 20, 30, 0.95);
            backdrop-filter: blur(25px);
            border-radius: 24px;
            border: 1px solid rgba(255,255,255,0.12);
            box-shadow: 0 30px 60px rgba(0,0,0,0.6);
            position: relative;
        }

        .cart-container::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            height: 4px;
            background: linear-gradient(90deg, #ff6bcb, #43e97b, #667eea, #ff6bcb);
            background-size: 300% 100%;
            animation: shimmer 2.5s linear infinite;
            border-radius: 24px 24px 0 0;
        }

        @keyframes shimmer {
            0% { background-position: 200% 0; }
            100% { background-position: -200% 0; }
        }

        /* 🧾 CART ITEM */
        .cart-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1.4rem 0;
            border-bottom: 1px solid rgba(255,255,255,0.08);
        }

        .cart-item:last-child {
            border-bottom: none;
        }

        .cart-item-details h3 {
            font-size: 1.25rem;
            font-weight: 800;
            margin-bottom: 0.4rem;
        }

        .cart-item-details p {
            font-size: 0.95rem;
            color: #b0b0b8;
        }

        /* ➕➖ QUANTITY */
        .quantity-controls {
            display: flex;
            align-items: center;
            gap: 0.8rem;
        }

        .quantity-controls p {
            font-weight: 700;
            min-width: 20px;
            text-align: center;
        }

        .quantity-btn {
            width: 34px;
            height: 34px;
            border-radius: 50%;
            border: 1px solid #ff6bcb;
            background: transparent;
            color: #ff6bcb;
            font-size: 18px;
            cursor: pointer;
            transition: all 0.25s ease;
        }

        .quantity-btn:hover {
            background: linear-gradient(135deg, #ff6bcb, #f8b500);
            color: #020617;
            transform: scale(1.1);
        }

        .quantity-btn:disabled {
            border-color: #555;
            color: #555;
            cursor: not-allowed;
        }

        /* ❌ REMOVE */
        .remove-btn {
            background: transparent;
            border: 1px solid #f87171;
            color: #f87171;
            padding: 8px 16px;
            border-radius: 14px;
            font-weight: 700;
            cursor: pointer;
            transition: all 0.25s ease;
        }

        .remove-btn:hover {
            background: linear-gradient(135deg, #f87171, #ef4444);
            color: #020617;
            transform: scale(1.05);
        }

        /* 💰 TOTAL */
        .total {
            text-align: right;
            font-size: 1.4rem;
            font-weight: 900;
            margin-top: 1.8rem;
            color: #43e97b;
        }

        /* 🔘 BUTTONS */
        .btn {
            display: inline-block;
            width: 100%;
            margin-top: 1.6rem;
            padding: 14px;
            text-align: center;
            background: linear-gradient(135deg, #ff6bcb, #667eea);
            color: white;
            border: none;
            border-radius: 16px;
            font-size: 1rem;
            font-weight: 800;
            cursor: pointer;
            text-decoration: none;
            transition: all 0.35s ease;
            box-shadow: 0 8px 25px rgba(255,107,203,0.35);
        }

        .btn:hover {
            transform: translateY(-4px);
            box-shadow: 0 14px 35px rgba(255,107,203,0.55);
        }

        .btn:active {
            transform: translateY(1px);
        }

        .add-more-items {
            margin-top: 1.4rem;
            text-align: center;
        }

        @media (max-width: 768px) {
            .cart-item {
                flex-direction: column;
                align-items: flex-start;
                gap: 1rem;
            }

            .total {
                text-align: center;
            }
        }
    </style>
</head>

<body>

<h1>Your Cart</h1>

<div class="cart-container">

<%
    Cart cart = (Cart) session.getAttribute("cart");
    Integer restaurantId = (Integer) session.getAttribute("oldRestaurantId");

    if (cart != null && !cart.getItems().isEmpty()) {
        for (CartItem item : cart.getItems().values()) {
%>

    <div class="cart-item">
        <div class="cart-item-details">
            <h3><%= item.getName() %></h3>
            <p>Price: ₹<%= item.getPrice() %></p>
            <p>Total: ₹<%= item.getTotalPrice() %></p>
        </div>

        <div class="quantity-controls">
            <form action="cart" style="display:inline;">
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="restaurantId" value="<%=restaurantId %>">
                <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                <button class="quantity-btn">+</button>
            </form>

            <p><%= item.getQuantity() %></p>

            <form action="cart" style="display:inline;">
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="restaurantId" value="<%=restaurantId %>">
                <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                <button class="quantity-btn" <% if (item.getQuantity() == 1) { %> disabled <% } %>>-</button>
            </form>
        </div>

        <form action="cart">
            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
            <input type="hidden" name="restaurantId" value="<%=restaurantId%>">
            <input type="hidden" name="action" value="remove">
            <button class="remove-btn">Remove</button>
        </form>
    </div>

<%
        }
%>

    <div class="total">
        Grand Total : ₹<%= cart.getTotalPrice() %>
    </div>

    <div class="add-more-items">
        <a href="menu?restaurantId=<%= restaurantId %>" class="btn">Add More Items</a>
    </div>

<%
    } else {
%>

    <p style="text-align:center; color:#9ca3af;">Your cart is empty.</p>
    <div class="add-more-items">
        <a href="restaurant" class="btn">Add Items</a>
    </div>

<%
    }

    if (session.getAttribute("cart") != null) {
%>

    <form action="checkout.jsp" method="post">
        <input type="submit" value="Proceed to Checkout" class="btn">
    </form>

<%
    }
%>

</div>

</body>
</html>
