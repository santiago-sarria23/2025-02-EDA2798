import java.util.Scanner;

import model.Account;
import model.Balance;
import model.Cards;
import model.Loans;
import repositories.BalanceRepository;
import repositories.CardsRepository;
import repositories.LoansRepository;
import services.AccountService;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.math.BigDecimal;

public class App {
    private static AccountService accountService = new AccountService();
    private static BalanceRepository balanceRepository = new BalanceRepository();
    private static CardsRepository cardsRepository = new CardsRepository();
    private static LoansRepository loansRepository = new LoansRepository();
    public static void main(String[] args) throws Exception {
        /*accountService.findAll().stream().forEach(a->System.out.println(a));
        Account account = new Account("ACC010", "Johanny Valencia", "johanny.valencia@example.com", "3000000001",
                "Savings", "Calle 20 de Turbaco-Bolivar");
        accountService.save(account);
        System.out.println("*".repeat(100));
        accountService.findAll().stream().forEach(a->System.out.println(a));
        System.out.println("-".repeat(100));
        accountService.findById("ACC009").ifPresentOrElse(
                acc -> System.out.println("Encontrado: " + acc),
                () -> System.out.println(" account number no encontrado."));
        accountService.deleteById("ACC010");
        System.out.println("/".repeat(100));
        accountService.findAll().stream().forEach(System.out::println);*/

        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMainMenu();
                String option = sc.nextLine().trim();
                switch (option) {
                    case "1":
                        runCrudMenu(sc, "Account");
                        break;
                    case "2":
                        runCrudMenu(sc, "Balance");
                        break;
                    case "3":
                        runCrudMenu(sc, "Loans");
                        break;
                    case "4":
                        runCrudMenu(sc, "Cards");
                        break;
                    case "0":
                        running = false;
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        }

    }

        private static void printMainMenu() {
        System.out.println("\n=== Menú Principal ===");
        System.out.println("1. Account");
        System.out.println("2. Balance");
        System.out.println("3. Loans");
        System.out.println("4. Cards");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void runCrudMenu(Scanner sc, String entityName) {
        boolean back = false;
        while (!back) {
            printCrudMenu(entityName);
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1": // Create
                    if ("Account".equalsIgnoreCase(entityName)) {
                        System.out.println("[Account] Crear");
                        System.out.print("Account number: ");
                        String newAccNum = sc.nextLine().trim();
                        if (newAccNum.isEmpty()) {
                            System.out.println("Account number no puede estar vacío.");
                            break;
                        }
                        if (accountService.findById(newAccNum).isPresent()) {
                            System.out.println("Ya existe una cuenta con accountNumber=" + newAccNum);
                            break;
                        }
                        System.out.print("Nombre: ");
                        String newName = sc.nextLine().trim();
                        System.out.print("Email: ");
                        String newEmail = sc.nextLine().trim();
                        System.out.print("Mobile number: ");
                        String newMobile = sc.nextLine().trim();
                        System.out.print("Account type: ");
                        String newType = sc.nextLine().trim();
                        System.out.print("Address: ");
                        String newAddress = sc.nextLine().trim();

                        Account newAccount = new Account(newAccNum, newName, newEmail, newMobile, newType, newAddress);
                        accountService.save(newAccount);
                        System.out.println("Cuenta creada: " + newAccount);
                    } else if ("Balance".equalsIgnoreCase(entityName)) {
                        System.out.println("[Balance] Crear");
                        try {
                            System.out.print("Date (yyyy-MM-dd): ");
                            String dateStr = sc.nextLine().trim();
                            LocalDate date = LocalDate.parse(dateStr);
                            System.out.print("Description: ");
                            String desc = sc.nextLine().trim();
                            System.out.print("Cash in: ");
                            BigDecimal cashIn = new BigDecimal(sc.nextLine().trim());
                            System.out.print("Cash out: ");
                            BigDecimal cashOut = new BigDecimal(sc.nextLine().trim());
                            System.out.print("Closing balance: ");
                            BigDecimal closing = new BigDecimal(sc.nextLine().trim());
                            Balance b = new Balance();
                            b.setDate(date);
                            b.setDescription(desc);
                            b.setCashIn(cashIn);
                            b.setCashOut(cashOut);
                            b.setClosingBalance(closing);
                            balanceRepository.save(b);
                            System.out.println("Balance creado: " + b);
                        } catch (DateTimeParseException | NumberFormatException ex) {
                            System.out.println("Entrada inválida: " + ex.getMessage());
                        }
                    } else if ("Cards".equalsIgnoreCase(entityName) || "Card".equalsIgnoreCase(entityName)) {
                        System.out.println("[Cards] Crear");
                        try {
                            System.out.print("Card number: ");
                            String cardNum = sc.nextLine().trim();
                            System.out.print("Type (Debit/Credit): ");
                            String type = sc.nextLine().trim();
                            System.out.print("Total limit: ");
                            BigDecimal totalLimit = new BigDecimal(sc.nextLine().trim());
                            System.out.print("Amount used: ");
                            BigDecimal amountUsed = new BigDecimal(sc.nextLine().trim());
                            System.out.print("Available: ");
                            BigDecimal available = new BigDecimal(sc.nextLine().trim());
                            Cards c = new Cards();
                            c.setCardNumber(cardNum);
                            c.setType(type);
                            c.setTotalLimit(totalLimit);
                            c.setAmountUsed(amountUsed);
                            c.setAvailable(available);
                            cardsRepository.save(c);
                            System.out.println("Card creada: " + c);
                        } catch (NumberFormatException ex) {
                            System.out.println("Entrada numérica inválida: " + ex.getMessage());
                        }
                    } else if ("Loans".equalsIgnoreCase(entityName) || "Loan".equalsIgnoreCase(entityName)) {
                        System.out.println("[Loans] Crear");
                        try {
                            System.out.print("Date (yyyy-MM-dd): ");
                            LocalDate date = LocalDate.parse(sc.nextLine().trim());
                            System.out.print("Type: ");
                            String type = sc.nextLine().trim();
                            System.out.print("Total loan: ");
                            BigDecimal totalLoan = new BigDecimal(sc.nextLine().trim());
                            System.out.print("Amount paid: ");
                            BigDecimal amountPaid = new BigDecimal(sc.nextLine().trim());
                            System.out.print("Outstanding amount: ");
                            BigDecimal outstanding = new BigDecimal(sc.nextLine().trim());
                            Loans l = new Loans();
                            l.setDate(date);
                            l.setType(type);
                            l.setTotalLoan(totalLoan);
                            l.setAmountPaid(amountPaid);
                            l.setOutstandingAmt(outstanding);
                            loansRepository.save(l);
                            System.out.println("Loan creado: " + l);
                        } catch (DateTimeParseException | NumberFormatException ex) {
                            System.out.println("Entrada inválida: " + ex.getMessage());
                        }
                    } else {
                        System.out.println("Entidad no soportada: " + entityName);
                    }
                    break;
                case "2": // Read by id
                    System.out.print("[" + entityName + "] Leer por id - ingrese id: ");
                    String id = sc.nextLine().trim();
                    if (id.isEmpty()) {
                        System.out.println("Id no puede estar vacío.");
                        break;
                    }
                    if ("Account".equalsIgnoreCase(entityName)) {
                        accountService.findById(id).ifPresentOrElse(
                                acc -> System.out.println("Encontrado: " + acc),
                                () -> System.out.println(entityName + " con id=" + id + " no encontrado."));
                    } else if ("Balance".equalsIgnoreCase(entityName)) {
                        balanceRepository.findById(id).ifPresentOrElse(
                                b -> System.out.println("Encontrado: " + b),
                                () -> System.out.println(entityName + " con id=" + id + " no encontrado."));
                    } else if ("Cards".equalsIgnoreCase(entityName) || "Card".equalsIgnoreCase(entityName)) {
                        cardsRepository.findById(id).ifPresentOrElse(
                                c -> System.out.println("Encontrado: " + c),
                                () -> System.out.println(entityName + " con id=" + id + " no encontrado."));
                    } else if ("Loans".equalsIgnoreCase(entityName) || "Loan".equalsIgnoreCase(entityName)) {
                        loansRepository.findById(id).ifPresentOrElse(
                                l -> System.out.println("Encontrado: " + l),
                                () -> System.out.println(entityName + " con id=" + id + " no encontrado."));
                    } else {
                        System.out.println("Entidad no soportada: " + entityName);
                    }
                    break;
                case "3": // List all
                    if ("Account".equalsIgnoreCase(entityName)) {
                        accountService.findAll().forEach(System.out::println);
                    } else if ("Balance".equalsIgnoreCase(entityName)) {
                        balanceRepository.findAll().forEach(System.out::println);
                    } else if ("Cards".equalsIgnoreCase(entityName) || "Card".equalsIgnoreCase(entityName)) {
                        cardsRepository.findAll().forEach(System.out::println);
                    } else if ("Loans".equalsIgnoreCase(entityName) || "Loan".equalsIgnoreCase(entityName)) {
                        loansRepository.findAll().forEach(System.out::println);
                    } else {
                        System.out.println("Entidad no soportada: " + entityName);
                    }
                    break;
                case "4": // Update
                    System.out.print("[" + entityName + "] Actualizar - ingrese id: ");
                    String idUp = sc.nextLine().trim();
                    if (idUp.isEmpty()) {
                        System.out.println("Id no puede estar vacío.");
                        break;
                    }
                    if ("Account".equalsIgnoreCase(entityName)) {
                        accountService.findById(idUp).ifPresentOrElse(existing -> {
                            System.out.println("Actualizando cuenta: " + existing);
                            System.out.print("Nombre (actual: " + existing.getName() + ") new: ");
                            String upName = sc.nextLine().trim();
                            if (!upName.isEmpty()) existing.setName(upName);
                            System.out.print("Email (actual: " + existing.getEmail() + ") new: ");
                            String upEmail = sc.nextLine().trim();
                            if (!upEmail.isEmpty()) existing.setEmail(upEmail);
                            System.out.print("Mobile number (actual: " + existing.getMobileNumber() + ") new: ");
                            String upMobile = sc.nextLine().trim();
                            if (!upMobile.isEmpty()) existing.setMobileNumber(upMobile);
                            System.out.print("Account type (actual: " + existing.getAccountType() + ") new: ");
                            String upType = sc.nextLine().trim();
                            if (!upType.isEmpty()) existing.setAccountType(upType);
                            System.out.print("Address (actual: " + existing.getAddress() + ") new: ");
                            String upAddress = sc.nextLine().trim();
                            if (!upAddress.isEmpty()) existing.setAddress(upAddress);

                            accountService.save(existing);
                            System.out.println("Cuenta actualizada: " + existing);
                        }, () -> System.out.println(entityName + " con id=" + idUp + " no encontrado."));
                    } else if ("Balance".equalsIgnoreCase(entityName)) {
                        balanceRepository.findById(idUp).ifPresentOrElse(existing -> {
                            System.out.println("Actualizando balance: " + existing);
                            try {
                                System.out.print("Description (actual: " + existing.getDescription() + ") new: ");
                                String d = sc.nextLine().trim();
                                if (!d.isEmpty()) existing.setDescription(d);
                                System.out.print("Cash in (actual: " + existing.getCashIn() + ") new: ");
                                String ci = sc.nextLine().trim();
                                if (!ci.isEmpty()) existing.setCashIn(new BigDecimal(ci));
                                System.out.print("Cash out (actual: " + existing.getCashOut() + ") new: ");
                                String co = sc.nextLine().trim();
                                if (!co.isEmpty()) existing.setCashOut(new BigDecimal(co));
                                System.out.print("Closing balance (actual: " + existing.getClosingBalance() + ") new: ");
                                String cb = sc.nextLine().trim();
                                if (!cb.isEmpty()) existing.setClosingBalance(new BigDecimal(cb));
                                balanceRepository.save(existing);
                                System.out.println("Balance actualizado: " + existing);
                            } catch (NumberFormatException ex) {
                                System.out.println("Entrada numérica inválida: " + ex.getMessage());
                            }
                        }, () -> System.out.println(entityName + " con id=" + idUp + " no encontrado."));
                    } else if ("Cards".equalsIgnoreCase(entityName) || "Card".equalsIgnoreCase(entityName)) {
                        cardsRepository.findById(idUp).ifPresentOrElse(existing -> {
                            System.out.println("Actualizando card: " + existing);
                            try {
                                System.out.print("Type (actual: " + existing.getType() + ") new: ");
                                String t = sc.nextLine().trim();
                                if (!t.isEmpty()) existing.setType(t);
                                System.out.print("Total limit (actual: " + existing.getTotalLimit() + ") new: ");
                                String tl = sc.nextLine().trim();
                                if (!tl.isEmpty()) existing.setTotalLimit(new BigDecimal(tl));
                                System.out.print("Amount used (actual: " + existing.getAmountUsed() + ") new: ");
                                String au = sc.nextLine().trim();
                                if (!au.isEmpty()) existing.setAmountUsed(new BigDecimal(au));
                                System.out.print("Available (actual: " + existing.getAvailable() + ") new: ");
                                String av = sc.nextLine().trim();
                                if (!av.isEmpty()) existing.setAvailable(new BigDecimal(av));
                                cardsRepository.save(existing);
                                System.out.println("Card actualizado: " + existing);
                            } catch (NumberFormatException ex) {
                                System.out.println("Entrada numérica inválida: " + ex.getMessage());
                            }
                        }, () -> System.out.println(entityName + " con id=" + idUp + " no encontrado."));
                    } else if ("Loans".equalsIgnoreCase(entityName) || "Loan".equalsIgnoreCase(entityName)) {
                        loansRepository.findById(idUp).ifPresentOrElse(existing -> {
                            System.out.println("Actualizando loan: " + existing);
                            try {
                                System.out.print("Type (actual: " + existing.getType() + ") new: ");
                                String t = sc.nextLine().trim();
                                if (!t.isEmpty()) existing.setType(t);
                                System.out.print("Total loan (actual: " + existing.getTotalLoan() + ") new: ");
                                String tl = sc.nextLine().trim();
                                if (!tl.isEmpty()) existing.setTotalLoan(new BigDecimal(tl));
                                System.out.print("Amount paid (actual: " + existing.getAmountPaid() + ") new: ");
                                String ap = sc.nextLine().trim();
                                if (!ap.isEmpty()) existing.setAmountPaid(new BigDecimal(ap));
                                System.out.print("Outstanding (actual: " + existing.getOutstandingAmt() + ") new: ");
                                String out = sc.nextLine().trim();
                                if (!out.isEmpty()) existing.setOutstandingAmt(new BigDecimal(out));
                                loansRepository.save(existing);
                                System.out.println("Loan actualizado: " + existing);
                            } catch (NumberFormatException ex) {
                                System.out.println("Entrada numérica inválida: " + ex.getMessage());
                            }
                        }, () -> System.out.println(entityName + " con id=" + idUp + " no encontrado."));
                    } else {
                        System.out.println("Entidad no soportada: " + entityName);
                    }
                    break;
                case "5": // Delete
                    System.out.print("[" + entityName + "] Eliminar - ingrese id: ");
                    String idDel = sc.nextLine().trim();
                    if (idDel.isEmpty()) {
                        System.out.println("Id no puede estar vacío.");
                        break;
                    }
                    boolean deleted = false;
                    if ("Account".equalsIgnoreCase(entityName)) {
                        deleted = accountService.deleteById(idDel);
                    } else if ("Balance".equalsIgnoreCase(entityName)) {
                        deleted = balanceRepository.deleteById(idDel);
                    } else if ("Cards".equalsIgnoreCase(entityName) || "Card".equalsIgnoreCase(entityName)) {
                        deleted = cardsRepository.deleteById(idDel);
                    } else if ("Loans".equalsIgnoreCase(entityName) || "Loan".equalsIgnoreCase(entityName)) {
                        deleted = loansRepository.deleteById(idDel);
                    } else {
                        System.out.println("Entidad no soportada: " + entityName);
                    }
                    if (deleted) {
                        System.out.println(entityName + " con id=" + idDel + " eliminado.");
                    } else {
                        System.out.println(entityName + " con id=" + idDel + " no encontrado.");
                    }
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void printCrudMenu(String entityName) {
        System.out.println("\n--- " + entityName + " CRUD ---");
        System.out.println("1. Create");
        System.out.println("2. Read by id");
        System.out.println("3. List all");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Seleccione una opción: ");
    }
}
