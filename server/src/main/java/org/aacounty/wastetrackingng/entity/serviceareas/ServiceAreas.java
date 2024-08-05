package org.aacounty.wastetrackingng.entity.serviceareas;

// For spring boot 3 and java 17
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import jakarta.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
// @SequenceGenerator(name = "service_areas_seq", sequenceName =
// "\"SERVICE_AREAS_SEQ\"", allocationSize = 1)
// @GeneratedValue(strategy = GenerationType.IDENTITY)
public class ServiceAreas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seqNo;

    private String serviceArea;

    public ServiceAreas() {
    }

    public ServiceAreas(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    @Override
    public String toString() {
        return "ServiceArea [seqNo=" + seqNo + ", serviceArea=" + serviceArea + "]";
    }
}
