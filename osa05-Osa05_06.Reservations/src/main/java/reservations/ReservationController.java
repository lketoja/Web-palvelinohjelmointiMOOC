package reservations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {
    
    @Autowired
    private AccountRepository accountRepo;
   
    @Autowired
    private ReservationRepository reservationRepo;
    
    @GetMapping("/reservations")
    public String showReservations(Model model){
        model.addAttribute("reservations", reservationRepo.findAll());
        return "reservations";
    }

    
    @PostMapping("/reservations")
    public String addReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationTo) {
        
        //selvitetään kuka teki varauksen
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Account user = accountRepo.findByUsername(username);
        
        if(reservationFrom != null && reservationTo != null &&
                reservationRepo.findOverlappingReservations(reservationFrom, reservationTo).isEmpty()){
            reservationRepo.save(new Reservation(user, reservationFrom, reservationTo));
        }
        
//        List<Reservation> reservations = reservationRepo.findAll();
//        
//        if(reservations == null){//varauksia ei vielä ole
//            reservationRepo.save(new Reservation(user, reservationFrom, reservationTo));
//            return "redirect:/reservations";
//        }
//        
//        //järjestetään varaukset loppumisajan mukaan
//        Comparator<Reservation> compareByReservationTo = (Reservation r1, Reservation r2) ->
//                r1.getReservationTo().compareTo(r2.getReservationTo());
//        Collections.sort(reservations, compareByReservationTo);
//        
//        //jos uusi varaus on ennen kaikkia muita varauksia, tehdään se ja palataan
//        if(reservationTo.isBefore(reservations.get(0).getReservationFrom())){
//            reservationRepo.save(new Reservation(user, reservationFrom, reservationTo));
//            return "redirect:/reservations";
//        }
//        
//        //haetaan varauslistasta ensimmäinen varaus, jonka loppumisaika on 
//        //ennen uuden varauksen alkamista.
//        Optional<Reservation> before = reservations.stream()
//                .filter(reservation -> reservationFrom.isAfter(reservation.getReservationTo()))
//                .findFirst();
//        
//        if(before == null){ //uusi varaus alkaisi ennen kuin viimeinen päättyy
//            return "redirect:/reservations";
//        }
//        
//        int indexOfBefore = reservations.indexOf(before.get());
//        //jos edellinen varaus on listan viimeinen, tehdään heti uusi varaus
//        if(indexOfBefore == reservations.size()-1){
//            reservationRepo.save(new Reservation(user, reservationFrom, reservationTo));
//            return "redirect:/reservations";
//        }
//        
//        Reservation after = reservations.get(indexOfBefore + 1);
//        //jos uusi varaus loppuu ennen kuin seuraava alkaa, tehdään varaus
//        if(reservationTo.isBefore(after.getReservationFrom())){
//            reservationRepo.save(new Reservation(user, reservationFrom, reservationTo));
//        }
        return "redirect:/reservations";
    }
    
    

}
