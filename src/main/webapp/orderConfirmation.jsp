<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmed</title>

    <style>
        /* ===== GLOBAL RESET ===== */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', -apple-system, BlinkMacSystemFont, sans-serif;
        }

        /* ===== BODY (SAME AS restaurant.jsp) ===== */
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

        /* ===== CONFIRMATION CONTAINER ===== */
        .confirmation-container {
            width: 450px;
            max-width: 90vw;
            padding: 50px 40px;
            background: rgba(20,20,30,0.95);
            backdrop-filter: blur(25px);
            border-radius: 26px;
            border: 1px solid rgba(255,255,255,0.12);
            text-align: center;
            box-shadow: 0 30px 60px rgba(0,0,0,0.6);
            animation: slideUp 0.8s cubic-bezier(0.25,0.46,0.45,0.94);
        }

        /* ===== SUCCESS ICON ===== */
        .success-icon {
            width: 100px;
            height: 100px;
            margin: 0 auto 30px;
            background: linear-gradient(135deg, #ff6bcb, #43e97b, #667eea);
            border-radius: 50%;
            position: relative;
            animation: pulse 2s infinite;
            box-shadow: 0 0 40px rgba(255,107,203,0.5);
        }

        .success-icon::before {
            content: "✓";
            position: absolute;
            inset: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 48px;
            font-weight: 900;
            color: #020617;
        }

        /* ===== HEADING ===== */
        h1 {
            font-size: 2.2rem;
            font-weight: 900;
            margin-bottom: 16px;
            background: linear-gradient(135deg, #ff6bcb, #43e97b, #667eea);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            letter-spacing: 1px;
        }

        /* ===== MESSAGE ===== */
        .message {
            color: #b0b0b8;
            font-size: 1rem;
            line-height: 1.7;
            margin-bottom: 36px;
        }

        /* ===== BUTTON (SAME AS restaurant.jsp CTA STYLE) ===== */
        .btn {
            display: inline-block;
            width: 100%;
            padding: 15px 30px;
            background: linear-gradient(135deg, #ff6bcb, #43e97b);
            color: #020617;
            border-radius: 50px;
            font-size: 1.05rem;
            font-weight: 800;
            text-decoration: none;
            transition: all 0.35s ease;
            box-shadow: 0 12px 35px rgba(255,107,203,0.4);
        }

        .btn:hover {
            transform: translateY(-5px) scale(1.03);
            box-shadow: 0 22px 50px rgba(67,233,123,0.6);
        }

        .btn:active {
            transform: scale(0.97);
        }

        /* ===== ANIMATIONS ===== */
        @keyframes slideUp {
            from {
                opacity: 0;
                transform: translateY(50px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes pulse {
            0%,100% { transform: scale(1); }
            50% { transform: scale(1.08); }
        }

        /* ===== RESPONSIVE ===== */
        @media (max-width: 480px) {
            .confirmation-container {
                padding: 40px 26px;
            }
            h1 {
                font-size: 1.9rem;
            }
        }
    </style>
</head>

<body>

    <div class="confirmation-container">
        <div class="success-icon"></div>

        <h1>Order Placed Successfully!</h1>

        <p class="message">
            Your delicious order has been confirmed and is being prepared.
            You'll receive order updates soon. Thank you for choosing us!
        </p>

        <a href="restaurant" class="btn">
            🍽️ Order More
        </a>
    </div>

</body>
</html>
