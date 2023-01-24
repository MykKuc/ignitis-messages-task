import spock.lang.Specification

class CheckThings extends Specification{

    def "Name"() {
       expect:
       Math.max(1,3) == 3
    }
}
