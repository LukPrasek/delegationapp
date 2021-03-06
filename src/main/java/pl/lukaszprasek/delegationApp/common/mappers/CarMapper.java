package pl.lukaszprasek.delegationApp.common.mappers;

import java.util.List;

public interface CarMapper <F, T> {
    T map (F from);
    List<T> mapList(List<F> from);
}
