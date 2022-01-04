package application;

import application.lib.Injector;
import application.model.CinemaHall;
import application.model.Movie;
import application.model.MovieSession;
import application.model.Order;
import application.model.User;
import application.security.AuthenticationService;
import application.service.CinemaHallService;
import application.service.MovieService;
import application.service.MovieSessionService;
import application.service.OrderService;
import application.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static final Injector injector = Injector.getInstance("application");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);

        CinemaHall firstCinemaHall = new CinemaHall();
        firstCinemaHall.setCapacity(100);
        firstCinemaHall.setDescription("first hall with capacity 100");

        CinemaHall secondCinemaHall = new CinemaHall();
        secondCinemaHall.setCapacity(200);
        secondCinemaHall.setDescription("second hall with capacity 200");

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(firstCinemaHall);
        cinemaHallService.add(secondCinemaHall);

        System.out.println(cinemaHallService.getAll());
        System.out.println(cinemaHallService.get(firstCinemaHall.getId()));

        MovieSession tomorrowMovieSession = new MovieSession();
        tomorrowMovieSession.setCinemaHall(firstCinemaHall);
        tomorrowMovieSession.setMovie(fastAndFurious);
        tomorrowMovieSession.setShowTime(LocalDateTime.now().plusDays(1L));

        MovieSession yesterdayMovieSession = new MovieSession();
        yesterdayMovieSession.setCinemaHall(firstCinemaHall);
        yesterdayMovieSession.setMovie(fastAndFurious);
        yesterdayMovieSession.setShowTime(LocalDateTime.now().minusDays(1L));

        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(tomorrowMovieSession);
        movieSessionService.add(yesterdayMovieSession);

        System.out.println(movieSessionService.get(yesterdayMovieSession.getId()));
        System.out.println(movieSessionService.findAvailableSessions(
                        fastAndFurious.getId(), LocalDate.now()));

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User testUser;
        try {
            testUser = authenticationService.register("userEmail@gmail.com", "userPassword");
        } catch (Exception e) {
            throw new RuntimeException("Unable to register");
        }
        try {
            System.out.println(authenticationService
                    .login("userEmail@gmail.com", "userPassword"));
        } catch (Exception e) {
            throw new RuntimeException("Unable to login", e);
        }

        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(tomorrowMovieSession, testUser);
        System.out.println(shoppingCartService.getByUser(testUser));
        shoppingCartService.clearShoppingCart(shoppingCartService.getByUser(testUser));
        System.out.println(shoppingCartService.getByUser(testUser));

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        Order order = orderService.completeOrder(shoppingCartService.getByUser(testUser));
        shoppingCartService.addSession(tomorrowMovieSession, testUser);
        shoppingCartService.addSession(yesterdayMovieSession, testUser);
        orderService.completeOrder(shoppingCartService.getByUser(testUser));
        System.out.println(orderService.getOrdersHistory(testUser));
    }
}
