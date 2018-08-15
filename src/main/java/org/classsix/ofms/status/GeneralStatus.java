package org.classsix.ofms.status;

import org.classsix.ofms.status.inter.StatusBase;

/**
 * Created by Jiang on 2018/5/28.
 * Coding
 */
public enum GeneralStatus implements StatusBase {
    SUCCESS(0), ERROR(1);

    private int status;

    GeneralStatus(int status){
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }
}
