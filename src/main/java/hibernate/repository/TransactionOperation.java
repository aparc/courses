package hibernate.repository;

public interface TransactionOperation {

    Object doInTransaction();

}
