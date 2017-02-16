package validationexample;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

/**
 * @author Cepro, 2017-02-15
 */
@Setter
@Configuration
@PropertySource(value = {"file:./config/config.properties"})
public class AppConfig {

    @Value("${val1}")
    private String val1;

    @Value("${val2}")
    private String val2;

    @Value("${count_num}")
    private String count;

    public String getVal1() {
        if (StringUtils.isEmpty(val1)) return "Value 1";
        return this.val1;
    }

    public String getVal2() {
        if (StringUtils.isEmpty(val2)) return "Value 2";
        return this.val2;
    }

    public String getCount() {
        return count;
    }
}
