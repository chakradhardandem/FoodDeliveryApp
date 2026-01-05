<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.Model.Restaurant" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        
        body {
            font-family: 'Segoe UI', -apple-system, BlinkMacSystemFont, sans-serif;
            background: #0a0a0f;
            color: #ffffff;
            min-height: 100vh;
            overflow-x: hidden;
            padding-top: 90px;
        }

        body::before {
            content: '';
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: 
                radial-gradient(circle at 20% 80%, rgba(120, 119, 198, 0.3) 0%, transparent 50%),
                radial-gradient(circle at 80% 20%, rgba(255, 119, 198, 0.3) 0%, transparent 50%),
                radial-gradient(circle at 40% 40%, rgba(120, 219, 255, 0.2) 0%, transparent 50%);
            z-index: -1;
            animation: float 20s ease-in-out infinite;
        }

        @keyframes float {0%, 100% { transform: scale(1) rotate(0deg); } 50% { transform: scale(1.1) rotate(180deg); } }

        /* SAME NAVBAR */
        .navbar { position: fixed; top: 0; width: 100%; background: rgba(10, 10, 15, 0.98); backdrop-filter: blur(25px); border-bottom: 1px solid rgba(255, 255, 255, 0.08); z-index: 1000; padding: 1.2rem 5%; }
        .nav-container { max-width: 1400px; margin: 0 auto; display: flex; justify-content: space-between; align-items: center; }
        .logo { font-size: 2.2rem; font-weight: 900; background: linear-gradient(135deg, #ff6bcb, #43e97b, #667eea); background-size: 200% 200%; -webkit-background-clip: text; -webkit-text-fill-color: transparent; background-clip: text; animation: gradientShift 3s ease-in-out infinite; }
        @keyframes gradientShift { 0%, 100% { background-position: 0% 50%; } 50% { background-position: 100% 50%; } }
        .nav-links { display: flex; list-style: none; gap: 2.5rem; }
        .nav-links a { text-decoration: none; color: #e0e0e0; font-weight: 600; padding: 0.8rem 1.8rem; border-radius: 50px; transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1); }
        .nav-links a:hover { background: linear-gradient(135deg, rgba(255, 107, 203, 0.15), rgba(67, 233, 123, 0.15)); color: #ffffff; transform: translateY(-2px); box-shadow: 0 8px 25px rgba(255, 107, 203, 0.25); }

        .container { max-width: 1400px; margin: 0 auto; padding: 2rem; }
        .header { text-align: center; margin-bottom: 4rem; }
        .header h1 { font-size: 3.5rem; background: linear-gradient(135deg, #ff6bcb, #43e97b, #667eea); -webkit-background-clip: text; -webkit-text-fill-color: transparent; margin-bottom: 1rem; font-weight: 900; }
        .header p { font-size: 1.3rem; color: #b0b0b8; }

        .restaurants-grid { display: grid; grid-template-columns: repeat(3, 1fr); gap: 2rem; width: 100%; max-width: 1200px; margin: 0 auto; }

        /* PERFECT PROFESSIONAL CARDS */
        .restaurant-card { 
            background: rgba(20, 20, 30, 0.95); 
            backdrop-filter: blur(25px); 
            border: 1px solid rgba(255, 255, 255, 0.12); 
            border-radius: 24px; 
            overflow: hidden; 
            transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275); 
            position: relative;
            height: 420px; /* FIXED HEIGHT FOR PERFECT ALIGNMENT */
            display: flex;
            flex-direction: column;
        }
        
        .restaurant-card::before { 
            content: ''; 
            position: absolute; 
            top: 0; 
            left: 0; 
            right: 0; 
            height: 4px; 
            background: linear-gradient(90deg, #ff6bcb, #43e97b, #667eea, #ff6bcb); 
            background-size: 300% 100%; 
            animation: shimmer 2.5s linear infinite; 
        }
        
        @keyframes shimmer { 0% { background-position: 200% 0; } 100% { background-position: -200% 0; } }
        
        .restaurant-card:hover { 
            transform: translateY(-12px) scale(1.02); 
            box-shadow: 0 30px 60px rgba(0, 0, 0, 0.6); 
            border-color: rgba(255, 107, 203, 0.4); 
        }

        /* PROFESSIONAL IMAGE */
        .restaurant-image { 
            width: 100%; 
            height: 200px; 
            position: relative; 
            overflow: hidden; 
            flex-shrink: 0;
        }
        
        .restaurant-image img { 
            width: 100%; 
            height: 100%; 
            object-fit: cover; 
            transition: transform 0.6s cubic-bezier(0.25, 0.46, 0.45, 0.94); 
        }
        
        .restaurant-card:hover .restaurant-image img { transform: scale(1.08); }

        /* 🎯 PERFECTLY BALANCED CARD CONTENT */
        .card-content {
            flex: 1;
            padding: 1.5rem 1.4rem 1.6rem;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        /* TOP SECTION: NAME + RATING */
        .card-top {
            margin-bottom: 0.8rem;
        }
        
        .restaurant-name { 
            font-size: 1.32rem; 
            font-weight: 800; 
            color: #ffffff;
            margin-bottom: 0.6rem; 
            line-height: 1.25;
            letter-spacing: -0.02em;
        }
        
        .rating { 
            display: inline-flex; 
            align-items: center; 
            gap: 0.5rem; 
            background: linear-gradient(135deg, #ff6bcb, #f8b500); 
            color: white; 
            padding: 0.4rem 1.1rem; 
            border-radius: 22px; 
            font-weight: 700; 
            font-size: 0.88rem; 
            box-shadow: 0 4px 14px rgba(255, 107, 203, 0.35);
        }

        /* MIDDLE SECTION: CUISINE + DELIVERY INFO */
        .card-middle {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: auto;
            padding-bottom: 0.8rem;
            border-bottom: 1px solid rgba(255,255,255,0.08);
        }
        
        .cuisine { 
            background: linear-gradient(135deg, rgba(255,107,203,0.2), rgba(255,107,203,0.1)); 
            color: #ff6bcb; 
            font-weight: 600; 
            font-size: 0.9rem; 
            padding: 0.35rem 0.85rem; 
            border-radius: 16px; 
            border: 1px solid rgba(255,107,203,0.3);
        }
        
        .delivery-info {
            display: flex;
            flex-direction: column;
            align-items: flex-end;
            gap: 0.3rem;
        }
        
        .free-delivery { 
            background: linear-gradient(135deg, #43e97b, #32c852); 
            color: white; 
            padding: 0.3rem 0.9rem; 
            border-radius: 14px; 
            font-size: 0.78rem; 
            font-weight: 700; 
            box-shadow: 0 3px 12px rgba(67, 233, 123, 0.3);
        }
        
        .eta { 
            color: #43e97b; 
            font-weight: 700; 
            font-size: 0.86rem; 
            display: flex; 
            align-items: center; 
            gap: 0.3rem; 
        }

        /* BOTTOM SECTION: LOCATION */
        .card-bottom {
            padding-top: 0.8rem;
        }
        
        .location { 
            font-size: 0.84rem; 
            color: #a0a0b0; 
            display: flex; 
            align-items: center; 
            gap: 0.4rem; 
            font-weight: 500; 
        }

        @media (max-width: 1200px) { .restaurants-grid { grid-template-columns: repeat(2, 1fr); gap: 1.6rem; } }
        @media (max-width: 768px) { 
            .restaurants-grid { grid-template-columns: 1fr; gap: 1.5rem; }
            .card-middle { flex-direction: column; align-items: flex-start; gap: 0.6rem; }
            .delivery-info { align-items: flex-start; }
        }
    </style>
</head>
<body>
    <!-- SAME NAVBAR -->
    <nav class="navbar">
        <div class="nav-container">
            <div class="logo">🍕 FoodieHub</div>
            <ul class="nav-links">
                <li><a href="restaurant">Home</a></li>
                <li><a href="cart.jsp">Cart</a></li>
            </ul>
        </div>
    </nav>

    <div class="container">
        <div class="header">
            <h1>Discover Top Restaurants</h1>
            <p>Premium restaurants with lightning-fast delivery in Anekal</p>
        </div>

        <div class="restaurants-grid">
            <% 
                List<Restaurant> allRestaurants = (List<Restaurant>)request.getAttribute("allRestaurants");
                if(allRestaurants != null) {
                    for(Restaurant restaurant : allRestaurants) {
            %>
            
            <div class="restaurant-card">
              <div class="restaurant-image">
              <a href="menu?restaurantId=<%=restaurant.getRestaurantID() %>">
                    <img src="<%= request.getContextPath() + "/" + restaurant.getImagePath() %>" alt="<%= restaurant.getrestaurantName() %>">
               </a>
                </div>
                
               
                <div class="card-content">
                   
                    <div class="card-top">
                        <h3 class="restaurant-name"><%=restaurant.getrestaurantName()%></h3>
                        <div class="rating">★ <%=restaurant.getRating()%></div>
                    </div>
                    
                  
                    <div class="card-middle">
                        <span class="cuisine"><%=restaurant.getcusineType()%></span>
                        <div class="delivery-info">
                            <div class="free-delivery">FREE DELIVERY</div>
                            <div class="eta">⚡ <%=restaurant.getDeliveryTime()%> min</div>
                        </div>
                    </div>
               
                    <div class="card-bottom">
                        <div class="location">📍 <%=restaurant.getAddress()%></div>
                    </div>
                </div>
            </div>
            
            <% 
                    }
                }
            %>
        </div>
    </div>
</body>
</html>
