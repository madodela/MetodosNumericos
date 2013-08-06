class RungeKutta
  class Punto
    attr_accessor :x, :y

    def initialize x, y
      @x = x
      @y = y
    end
  end

  attr_accessor :a, :b, :n, :alpha, :puntos

  def initialize a, b, n, alpha
    @a = a
    @b = b
    @n = n
    @alpha = alpha
    @puntos = {}
  end

  def fun t, w
  	return Float(w - (t*t) +1)
  end

  def rk_method
    t=a
   h = (b-a)/n.to_s.to_r.to_f
   w=alpha
   
   i=1
  puts "(#{t.round(2)},#{w.round(6)})"
 	while i<=n 
  	  k1 = (h * fun(t,w)).to_s.to_r.to_f
      k2 = (h * fun(t + h/2.to_s.to_r.to_f,w + k1/2.to_s.to_r.to_f))
      k3 = (h * fun(t + h/2.to_s.to_r.to_f,w + k2/2.to_s.to_r.to_f)) 
      k4 = (h * fun(t + h, w + k3)).to_s.to_r.to_f
      w = w + (k1 + 2 * k2 + 2 * k3 + k4) / 6.to_s.to_r.to_f;
      t = (a + i * h)
      puts "(#{t.round(2)},#{w.round(6)})"
      i = i + 1
    end
  end
end

ec = RungeKutta.new 0,2,10,0.5
ec.rk_method