package max.dirscan.scan.filter;

import max.dirscan.input.DirsValidator;

import java.nio.file.Path;
import java.util.List;

/**
 * Класс наследник для {@link DirExcludeFilter}
 * Используется по умолчанию в приложении для фильтрации найденных директорий
 */
public class DefaultDirExcludeFilter extends DirExcludeFilter {

    public DefaultDirExcludeFilter(List<Path> dirsToFilter, DirsValidator validator) {
        super(dirsToFilter, validator);
    }

    @Override
    protected boolean filterDir(Path path) {
        // Фильтруем директорию в случае, если фильтр
        return getDirsToFilter().contains(path) || // либо содержит данную директорию среди тех, которые нужно отфильтровать
                        dirsToFilter.stream()   // либо данная директория является дочерней для одной из тех, которые нужно отфильтровать
                                .anyMatch(parent -> isParentFor(path, parent));

    }

    private boolean isParentFor(Path mayBeChild, Path parent) {
        return mayBeChild.startsWith(parent);
    }
}
