<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>

<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Segoe UI', -apple-system, BlinkMacSystemFont, sans-serif;
    }

    body {
        min-height: 100vh;
        background: #0a0a0f;
        display: flex;
        justify-content: center;
        align-items: center;
        color: #ffffff;
        overflow: hidden;
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

    /* 🧾 CHECKOUT CONTAINER */
    .container {
        width: 420px;
        padding: 2.4rem 2.6rem;
        background: rgba(20, 20, 30, 0.95);
        backdrop-filter: blur(25px);
        border-radius: 24px;
        border: 1px solid rgba(255,255,255,0.12);
        box-shadow: 0 30px 60px rgba(0,0,0,0.6);
        position: relative;
        animation: fadeIn 0.9s ease;
    }

    .container::before {
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

    /* 🏷️ HEADING */
    .container h2 {
        text-align: center;
        margin-bottom: 2rem;
        font-size: 2.4rem;
        font-weight: 900;
        background: linear-gradient(135deg, #ff6bcb, #43e97b, #667eea);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }

    /* 🏷️ LABELS */
    label {
        display: block;
        margin-bottom: 8px;
        font-size: 0.9rem;
        font-weight: 600;
        color: #b0b0b8;
    }

    /* ✏️ INPUTS */
    textarea,
    select {
        width: 100%;
        padding: 14px;
        margin-bottom: 1.3rem;
        background: rgba(10,10,15,0.9);
        border: 1px solid rgba(255,255,255,0.15);
        border-radius: 14px;
        color: #ffffff;
        outline: none;
        transition: all 0.3s ease;
    }

    textarea {
        resize: none;
        height: 90px;
    }

    textarea:focus,
    select:focus {
        border-color: #ff6bcb;
        box-shadow: 0 0 0 3px rgba(255,107,203,0.25);
        transform: scale(1.02);
    }

    /* 🚀 SUBMIT BUTTON */
    input[type="submit"] {
        width: 100%;
        padding: 14px;
        margin-top: 0.6rem;
        background: linear-gradient(135deg, #ff6bcb, #667eea);
        border: none;
        border-radius: 30px;
        font-size: 1rem;
        font-weight: 800;
        color: white;
        cursor: pointer;
        transition: all 0.35s ease;
        box-shadow: 0 8px 25px rgba(255,107,203,0.35);
    }

    input[type="submit"]:hover {
        transform: translateY(-4px);
        box-shadow: 0 14px 35px rgba(255,107,203,0.55);
    }

    input[type="submit"]:active {
        transform: scale(0.97);
    }

    @keyframes fadeIn {
        from {
            opacity: 0;
            transform: translateY(20px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
</style>

</head>

<body>

<div class="container">
    <h2>Checkout</h2>

    <form action="checkout" method="post">

        <label for="address">Delivery Address</label>
        <textarea id="address" name="address" required></textarea>

        <label for="paymentMethod">Payment Method</label>
        <select id="paymentMethod" name="paymentMethod" required>
            <option value="">-- Select Payment Method --</option>
            <option value="Credit Card">Credit Card</option>
            <option value="Debit Card">Debit Card</option>
            <option value="Cash on Delivery">Cash on Delivery</option>
        </select>

        <input type="submit" value="Place Order">

    </form>
</div>

</body>
</html>
