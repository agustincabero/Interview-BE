package dev.agustincabero.abbe.abstractions;

public interface SoftDeletable {
    void setDeleted(boolean deleted);
    boolean isDeleted();
}
