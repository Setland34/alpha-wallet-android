package io.awallet.crypto.alphawallet.ui.widget.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import io.stormbird.token.entity.TicketRange;

/**
 * Created by James on 10/02/2018.
 */

/**
 * This should purely be a container class of NonFungibleToken
 *
 */
public class TicketRangeParcel implements Parcelable
{
    //public final int seatStart;
    //public int seatCount;
    public TicketRange range;

    public TicketRangeParcel(TicketRange t)
    {
        range = t;
    }

    private TicketRangeParcel(Parcel in)
    {
        Object[] readObjArray = in.readArray(Object.class.getClassLoader());
        List tIds = new ArrayList<>();
        for (Object o : readObjArray)
        {
            tIds.add((BigInteger)o);
        }

        Boolean isChecked = (in.readInt() == 1) ? true : false;
        Boolean isBurned = (in.readInt() == 1) ? true : false;

        String contractAddress = in.readString();

        range = new TicketRange(tIds, contractAddress, isBurned, isChecked);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeArray(range.tokenIds.toArray());
        dest.writeInt(range.isChecked ? 1:0);
        dest.writeInt(range.isBurned ? 1:0);
        dest.writeString(range.contractAddress);
    }

    public static final Creator<TicketRangeParcel> CREATOR = new Creator<TicketRangeParcel>() {
        @Override
        public TicketRangeParcel createFromParcel(Parcel in) {
            return new TicketRangeParcel(in);
        }

        @Override
        public TicketRangeParcel[] newArray(int size) {
            return new TicketRangeParcel[size];
        }
    };
}
