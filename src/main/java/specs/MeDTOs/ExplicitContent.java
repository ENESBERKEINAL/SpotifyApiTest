package specs.MeDTOs;

import com.fasterxml.jackson.annotation.*;

public class ExplicitContent {
    private boolean filterEnabled;
    private boolean filterLocked;

    @JsonProperty("filter_enabled")
    public boolean getFilterEnabled() { return filterEnabled; }
    @JsonProperty("filter_enabled")
    public void setFilterEnabled(boolean value) { this.filterEnabled = value; }

    @JsonProperty("filter_locked")
    public boolean getFilterLocked() { return filterLocked; }
    @JsonProperty("filter_locked")
    public void setFilterLocked(boolean value) { this.filterLocked = value; }
}
